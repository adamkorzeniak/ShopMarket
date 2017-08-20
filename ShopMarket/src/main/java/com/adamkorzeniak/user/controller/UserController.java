package com.adamkorzeniak.user.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adamkorzeniak.user.model.UserDTO;
import com.adamkorzeniak.user.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping("/user")
	public ResponseEntity<Principal> user(Principal user) {
		if (user == null) {
			return new ResponseEntity<Principal>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Principal>(user, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Void> registerUser(@RequestBody UserDTO userForm) {
		userService.register(userForm);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Boolean> loginUser(@RequestBody UserDTO userForm) {
		boolean successfulLogin = userService.login(userForm);

		return new ResponseEntity<Boolean>(successfulLogin, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> confirmUser(@RequestParam(name = "t") String token) {
		String username = userService.confirm(token);
		
		if (username.isEmpty()) {
			return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
		}
	    UserDTO user = new UserDTO();
	    user.setUsername(username);

		return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
	}
}
