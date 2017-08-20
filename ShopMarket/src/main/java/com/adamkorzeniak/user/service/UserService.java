package com.adamkorzeniak.user.service;

import com.adamkorzeniak.user.model.User;
import com.adamkorzeniak.user.model.UserDTO;

public interface UserService {

	void register(UserDTO userForm);
	boolean login(UserDTO userForm);
	String confirm(String token);
	User getUser(String username);
}
