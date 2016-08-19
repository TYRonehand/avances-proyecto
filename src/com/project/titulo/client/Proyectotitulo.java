package com.project.titulo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.project.titulo.client.home.HomeWidget;
import com.project.titulo.client.login.LoginWidget;
import com.project.titulo.client.menu.MenuUser;
import com.project.titulo.client.session.SessionControl;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Proyectotitulo implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() 
	{
		//si los datos aun estan guardados en cookiesentramos sin consultar
		if(!Cookies.getCookie("MOPuser").equals("") && !Cookies.getCookie("MOPmail").equals("") && !Cookies.getCookie("MOPname").equals("") && !Cookies.getCookie("MOPban").equals(""))
		{
			//si no esta baneado
			if(Cookies.getCookie("MOPban").equals("0"))
			{
				//clean all
				RootPanel.get("GWTcontainer").clear();
				// widget close session	
				RootPanel.get("GWTusername").add(new SessionControl(Cookies.getCookie("MOPname"),true));
				//menu widget
				RootPanel.get("GWTmenu").add(new MenuUser());
				//home Widget
				RootPanel.get("GWTcontainer").add(new HomeWidget());
				
			}
			else if(Cookies.getCookie("MOPban").equals("0"))
			{
				//alert si el usuario a sido baneado
				Window.alert("We are sorry! but you are banned temporaly.");
			}
			else{

				//cualquier otro caso sera enviado al login
				RootPanel.get("GWTcontainer").add(new LoginWidget());
				// widget close session	
				RootPanel.get("GWTusername").clear();	
			}
			
		}
		else{

			//cualquier otro caso sera enviado al login
			RootPanel.get("GWTcontainer").add(new LoginWidget());
			// widget close session	
			RootPanel.get("GWTusername").clear();		
		}
		
	}
}
