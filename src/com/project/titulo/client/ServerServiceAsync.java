package com.project.titulo.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.project.titulo.client.model.User;
import com.project.titulo.client.model.UserFile;

/**
 * The async counterpart of <code>ServiceService</code>.
 */
public interface ServerServiceAsync {
	//
	void userExist(String mail, String pass, AsyncCallback<Boolean> callback) throws IllegalArgumentException;

	void authenticateUser(String user, String pass, AsyncCallback<User>	callback) throws IllegalArgumentException;
	
	void getUserInfo(String user, AsyncCallback<User>	callback) throws IllegalArgumentException;
	
	void getUserFiles(String user, AsyncCallback<List<UserFile>> callback) throws IllegalArgumentException;
	
	void setUserInfo(User myUser, AsyncCallback<Boolean> callback) throws IllegalArgumentException;
}
