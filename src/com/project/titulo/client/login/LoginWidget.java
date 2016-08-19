package com.project.titulo.client.login;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.project.titulo.client.ServerService;
import com.project.titulo.client.ServerServiceAsync;
import com.project.titulo.client.home.HomeWidget;
import com.project.titulo.client.menu.MenuUser;
import com.project.titulo.client.model.User;
import com.project.titulo.client.recovery.RecoveryWidget;
import com.project.titulo.client.register.RegisterWidget;
import com.project.titulo.client.session.SessionControl;

public class LoginWidget extends Composite{

	//elementos uibinder
	@UiField TextBox mailInput; 
	@UiField Label labelError1;  
	@UiField PasswordTextBox passInput;  
	@UiField Label labelError2;
	@UiField Hyperlink registerLink;
	@UiField Hyperlink recoveryLink;
	
	//RPC
	private final ServerServiceAsync serverService = GWT.create(ServerService.class);
	
	
	//crear widget
	private static LoginWidgetUiBinder uiBinder = GWT
			.create(LoginWidgetUiBinder.class);
	
	interface LoginWidgetUiBinder extends UiBinder<Widget, LoginWidget> {
	}
	
	//css
	@UiField(provided = true)
	final LoginResource res;
	
	//iniciador
	public LoginWidget() 
	{
		this.res = GWT.create(LoginResource.class);
	    res.style().ensureInjected();
	      
		initWidget(uiBinder.createAndBindUi(this));
		
	}
	//limpiar inputs
	private void clearInputs()
	{
		mailInput.setText("");
		passInput.setText("");
	}
	
	//evento cambio valor  input
	@UiHandler("mailInput")
    void handleMailInputChange(ValueChangeEvent<String> event) 
	{
      if (event.getValue().length() < 6) {
    	  labelError1.setVisible(true);
    	  labelError1.setText("minimum lenght 6");
      } else {
    	  labelError1.setVisible(false);
          labelError1.setText("");
      }
   }

	//evento cambio valor  input
	@UiHandler("passInput")
    void handlePassInputChange(ValueChangeEvent<String> event) 
	{
      if (event.getValue().length() < 6) {
    	  labelError2.setText("minimum lenght 6");
    	  labelError2.setVisible(true);
      } else {
          labelError2.setText("");
    	  labelError2.setVisible(false);
      }
   }
	
	//click registro link
	@UiHandler("registerLink")
	void onRegisteLinkClick(ClickEvent event) 
	{
		RootPanel.get("GWTcontainer").clear();
		RootPanel.get("GWTcontainer").add(new RegisterWidget());
	}
	
	//click recuperar link
	@UiHandler("recoveryLink")
	void onRecoveryLinkClick(ClickEvent event) 
	{
		RootPanel.get("GWTcontainer").clear();
		RootPanel.get("GWTcontainer").add(new RecoveryWidget());
	}
	
	/*Evento click SUBMIT*/
	@UiHandler("submitBTN")
	void onSubmitBTNClick(ClickEvent event) 
	{
		Cookies.removeCookie("MOPuser");
		Cookies.removeCookie("MOPmail");
		Cookies.removeCookie("MOPname");
		Cookies.removeCookie("MOPban");
		//no existen errores
		if(passInput.getText().length()>=6 && mailInput.getText().length()>=6)
		{
			//consulta datos
			serverService.authenticateUser( mailInput.getText(), passInput.getText(), new AsyncCallback<User>()
			{

				@Override
				public void onFailure(Throwable caught) {
					Window.alert(" UPS! Server offline");
					
					//limpiar input
					clearInputs();
				}

				@Override
				public void onSuccess(User result) 
				{
					//validaciones
					if(result!=null)
					{
						//guardamos las cookies con info
						Cookies.setCookie("MOPuser", result.getId(), new Date(new Date().getTime()+1000*60*60*2));
						Cookies.setCookie("MOPmail", result.getMail(), new Date(new Date().getTime()+1000*60*60*2));
						Cookies.setCookie("MOPname", result.getName()+" "+result.getLastname(), new Date(new Date().getTime()+1000*60*60*2));
						Cookies.setCookie("MOPban", result.getBanned(), new Date(new Date().getTime()+1000*60*60*2));
						
						if(result.getBanned()=="0")
						{

							// widget close session	
							RootPanel.get("GWTusername").add(new SessionControl(Cookies.getCookie("MOPname"),true));
							//clean
							// widget menu
							RootPanel.get("GWTmenu").add( new MenuUser());
							// widget  home
							RootPanel.get("GWTcontainer").clear();
							RootPanel.get("GWTcontainer").add(new HomeWidget());
							
						}
						else
						{
							Window.alert("Sorry but this account is temporaly banned");
						}
						
					}
					else
					{
						Window.alert(" User or password don't exist.");
						clearInputs();
					}
					
				}
				
			});

		}else{
			Window.alert("Login or Password is too short!");
		}
		
	}


}
