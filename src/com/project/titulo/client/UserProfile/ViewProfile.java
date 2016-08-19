package com.project.titulo.client.UserProfile;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ViewProfile extends Composite {

	private static ViewProfileUiBinder uiBinder = GWT
			.create(ViewProfileUiBinder.class);

	interface ViewProfileUiBinder extends UiBinder<Widget, ViewProfile> {
	}

	public ViewProfile() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
