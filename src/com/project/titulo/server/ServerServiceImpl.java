package com.project.titulo.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.project.titulo.client.ServerService;
import com.project.titulo.client.model.User;
import com.project.titulo.client.model.UserFile;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ServerServiceImpl extends RemoteServiceServlet implements ServerService {
	//datos conexion
	private Connection conn = null;
	//private String status;
	private String url = "jdbc:mysql://127.0.0.1:3306/proyectotitulo";
	private String user = "root";
	private String pass = "";
	
	//Constructor
	public ServerServiceImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) 
		{
		 	GWT.log(e.toString());
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) 
		{
		 	GWT.log(e.toString());
			e.printStackTrace();
		}
	}
	
	/*--------------RPC FUNCTIONS---------------*/
	
	/*USER*/
	//consulta si existe usuario
	@Override
	public Boolean userExist(String mail, String pass) throws IllegalArgumentException 
	{
		int count=0;
		try 
		 {
			 PreparedStatement ps = conn.prepareStatement("select mail from user where mail = '"+user+"' AND "+"password = '" + pass + "' limit 1");
			 ResultSet result = ps.executeQuery();
			 
			 while (result.next()) 
			 {
				 count++;
			 }
			 result.close();
			 ps.close();
			 
		 } 
		 catch (SQLException sqle) 
		 {
		 	GWT.log(sqle.toString());
		 	sqle.printStackTrace();
		 }
		
		if(count==0){
			 return false;
		}
		return true;
	}
	//user info validation
	@Override
	public User authenticateUser(String user, String pass) throws IllegalArgumentException
	{
		
		 User miuser = null;
		 
		 try 
		 {
			 //consultamos a base de datos
			 String Query= "SELECT * FROM user WHERE mail = '"+user+"' AND password = '" + pass + "' LIMIT 1";
			 PreparedStatement ps = conn.prepareStatement(Query);
			 ResultSet result = ps.executeQuery();
			 //recorremos resultado
			 while (result.next()) 
			 {
				 miuser = new User( result.getString("iduser"),
						 			result.getString("mail"), 
						 			result.getString("name"), 
						 			result.getString("lastname"), 
						 			"", 
						 			"", 
						 			"", 
						 			"",
						 			"",
						 			"",
						 			"",
						 			result.getString("banned")
						 		  );
			 }
			 result.close();
			 ps.close();
		 } 
		 catch (SQLException sqle) 
		 {
		 	GWT.log(sqle.toString());
		 	sqle.printStackTrace();
		 }
		 return miuser;
	}
	//user info no security
		@Override
	public User getUserInfo(String iduser) throws IllegalArgumentException {
			
			 User miuser = null;
			 try 
			 {
				 //consultamos a base de datos
				 String Query= "SELECT * FROM user WHERE iduser = '"+iduser+"' LIMIT 1";
				 PreparedStatement ps = conn.prepareStatement(Query);
				 ResultSet result = ps.executeQuery();
				 //recorremos resultado
				 while (result.next()) 
				 {
					 miuser = new User( 
							 			result.getString("iduser"),
							 			result.getString("mail"), 
							 			result.getString("name"), 
							 			result.getString("lastname"), 
							 			result.getString("country"), 
							 			result.getString("ocupation"), 
							 			result.getString("web"), 
							 			result.getString("university"),
							 			"",
							 			result.getString("creation"),
							 			result.getString("registered"),
							 			result.getString("banned")
							 		  );
				 }
				 result.close();
				 ps.close();
			 } 
			 catch (SQLException sqle) 
			 {
			 	GWT.log(sqle.toString());
			 	sqle.printStackTrace();
			 }
			 return miuser;
		}
	//update info user
	@Override
	public Boolean setUserInfo(User myUser) throws IllegalArgumentException {

		 try 
		 {
			 //actualizamos usuario
			 String Query= " UPDATE user SET "
			 			 + "name='"+myUser.getName()+"', "
			 			 + "lastname='"+myUser.getLastname()+"', "
			 			 + "country='"+myUser.getCountry()+"', "
			 			 + "ocupation='"+myUser.getOcupation()+"', "
			 			 + "web='"+myUser.getWeb()+"',"
			 			 + "university='"+myUser.getUniversity()+"' "
			 			 + "WHERE iduser="+myUser.getId()+" ";
			 
			//execute query
			PreparedStatement ps = conn.prepareStatement(Query);
			ResultSet result = ps.executeQuery();
			 
			result.close();
			ps.close();
		 } 
		 catch (SQLException sqle) 
		 {
		 	GWT.log(sqle.toString());
		 	sqle.printStackTrace();
		 	return false;
		 }
		 return true;
	}

	/*FILES------------------------------------------------------------------------------*/
	//all user files 
	@SuppressWarnings("null")
	@Override
	public List<UserFile> getUserFiles(String user) throws IllegalArgumentException {

		 List<UserFile> listFiles = null;
		 try 
		 {
			 //consultamos a base de datos
			 String Query= "SELECT * FROM datafile WHERE iduser = '"+user+"'";
			 PreparedStatement ps = conn.prepareStatement(Query);
			 ResultSet result = ps.executeQuery();
			 //recorremos resultado
			 while (result.next()) 
			 {
				 //create userfile
				 UserFile aux = new UserFile(   result.getString("iddatafile"), 
							 					result.getString("iduser"),
									 			result.getString("title"), 
									 			result.getString("description"),  
									 			result.getString("creation")
						 		  			);
				 //add element to list
				 listFiles.add(aux);
			 }
			 result.close();
			 ps.close();
		 } 
		 catch (SQLException sqle) 
		 {
		 	GWT.log(sqle.toString());
		 	sqle.printStackTrace();
		 }
		 return listFiles;
	}
}
