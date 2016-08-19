package com.project.titulo.client.register;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.project.titulo.client.login.LoginWidget;
import com.project.titulo.shared.FieldVerifier;

public class RegisterWidget extends Composite {

	@UiField  Label labelError1;
	@UiField  Label labelError2;
	@UiField  Label labelError3;
	@UiField  Label labelError4;
	@UiField  Label labelError5;
	@UiField  Hyperlink backLink;
	@UiField  Button registerBtn;
	@UiField  TextBox mailInput;
	
	
	private static RegisterWidgetUiBinder uiBinder = GWT
			.create(RegisterWidgetUiBinder.class);

	interface RegisterWidgetUiBinder extends UiBinder<Widget, RegisterWidget> {
	}

	public RegisterWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	//evento size name
	@UiHandler("nameInput")
    void handleNameChange(ValueChangeEvent<String> event) 
	{
	      if (event.getValue().length() < 3) {
	    	  labelError1.setText("minimum lenght 3");
	    	  labelError1.setVisible(true);
	      } else {
	          labelError1.setText("");
	    	  labelError1.setVisible(false);
	      }
   }
	//evento size lastname
	@UiHandler("lastnameInput")
    void handleLastnameChange(ValueChangeEvent<String> event) 
	{
		if (event.getValue().length() < 3) 
		{
			labelError1.setText("minimum lenght 3");
			labelError1.setVisible(true);
		} 
		else 
		{
			labelError1.setText("");
			labelError1.setVisible(false);
		}
	}
	//evento size lastname
	@UiHandler("mailInput")
    void handleMailChange(ValueChangeEvent<String> event) 
	{
		if(FieldVerifier.isValidMail(mailInput.getText()))
		{
			labelError1.setText("invalid e-mail");
			labelError1.setVisible(true);
		}	
		else if (event.getValue().length() < 6) 
		{
	    	labelError1.setText("minimum lenght 3");
	    	labelError1.setVisible(true);
	    } 
		else 
		{
	        labelError1.setText("");
	    	labelError1.setVisible(false);
	    }
   }
	
	
	
	
	//GO BACK
	@UiHandler("backLink")
	void onBackLinkClick(ClickEvent event) 
	{
		RootPanel.get("GWTcontainer").clear();
		RootPanel.get("GWTcontainer").add(new LoginWidget());
	}
	
	
	@UiHandler("registerBtn")
	void onRegisterBtnClick(ClickEvent event) 
	{
		
	}
}
