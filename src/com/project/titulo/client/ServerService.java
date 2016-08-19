package com.project.titulo.client;


import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.project.titulo.client.model.User;
import com.project.titulo.client.model.UserFile;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("service")
public interface ServerService extends RemoteService {
	
	//consultar existencia usuario
	Boolean userExist(String mail,String pass) throws IllegalArgumentException;
		
	//autenticacion
	User authenticateUser(String user, String pass) throws IllegalArgumentException;
	
	//get info
	User getUserInfo(String user) throws IllegalArgumentException;
	
	//set info
	Boolean setUserInfo(User myUser) throws IllegalArgumentException;
	
	//found user files 
	List<UserFile> getUserFiles(String user) throws IllegalArgumentException;
	
}
