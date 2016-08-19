package com.project.titulo.client.menu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.project.titulo.client.faq.FAQWidget;
import com.project.titulo.client.home.HomeWidget;
import com.project.titulo.client.upload.UploadFileWidget;

public class MenuUser extends Composite {

	private static MenuUserUiBinder uiBinder = GWT
			.create(MenuUserUiBinder.class);

	interface MenuUserUiBinder extends UiBinder<Widget, MenuUser> {
	}

	public MenuUser() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	//push files button 
	@UiHandler("filesBTN")
	void onButtonClick(ClickEvent event) {
		RootPanel.get("GWTcontainer").clear();
		RootPanel.get("GWTcontainer").add(new UploadFileWidget());
	}
	//push home button
	@UiHandler("homeBTN")
	void onHomeBTNClick(ClickEvent event) {

		//clean div
		RootPanel.get("GWTcontainer").clear();
		//home Widget
		RootPanel.get("GWTcontainer").add(new HomeWidget());
	}
	//push plot button
	@UiHandler("plotBTN")
	void onPlotBTNClick(ClickEvent event) {
		
	}
	//push metrics button
	@UiHandler("metricBTN")
	void onMetricBTNClick(ClickEvent event) {
		
	}
	//push community button
	@UiHandler("communityBTN")
	void onCommunityBTNClick(ClickEvent event) {
		
	}
	//push FAQ button
	@UiHandler("faqBTN")
	void onFaqBTNClick(ClickEvent event) {
		//clean div
		RootPanel.get("GWTcontainer").clear();
		//home Widget
		RootPanel.get("GWTcontainer").add(new FAQWidget());
	}
}
