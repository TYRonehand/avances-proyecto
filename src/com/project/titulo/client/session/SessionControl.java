package com.project.titulo.client.session;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.project.titulo.client.UserProfile.UserProfile;
import com.project.titulo.client.login.LoginWidget;

public class SessionControl extends Composite {

	@UiField Hyperlink exitLink;
	@UiField Hyperlink profileLink;
	
	
	private static SessionControlUiBinder uiBinder = GWT
			.create(SessionControlUiBinder.class);

	interface SessionControlUiBinder extends UiBinder<Widget, SessionControl> {
	}

	public SessionControl(String username, boolean open) {
		initWidget(uiBinder.createAndBindUi(this));
		if(open)
			profileLink.setText(username);
		else
			profileLink.setText("Log In");
	}
	
	private void closeSession() {
		
		Cookies.removeCookie("MOPuser");
		Cookies.removeCookie("MOPmail");
		Cookies.removeCookie("MOPname");
		Cookies.removeCookie("MOPban");
		RootPanel.get("GWTusername").clear();
		RootPanel.get("GWTcontainer").clear();
		RootPanel.get("GWTmenu").clear();
		
		RootPanel.get("GWTcontainer").add(new LoginWidget());
		
	}
	
	private void gotoProfile(){
		//goto profile
		RootPanel.get("GWTcontainer").clear();
		RootPanel.get("GWTcontainer").add(new UserProfile(Cookies.getCookie("MOPuser")));;
		
	}
	
	/*EVENT HANDLERS*/
	//click salir
	@UiHandler("exitLink")
	void onExitLinkClick(ClickEvent event) 
	{
		closeSession();
	}
	//click salir
	@UiHandler("profileLink")
	void onProfileLinkClick(ClickEvent event) 
	{
		gotoProfile();  
	}

}
