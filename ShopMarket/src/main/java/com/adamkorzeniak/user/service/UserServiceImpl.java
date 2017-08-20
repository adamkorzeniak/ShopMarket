package com.adamkorzeniak.user.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.adamkorzeniak.user.model.AccountConfirmation;
import com.adamkorzeniak.user.model.User;
import com.adamkorzeniak.user.model.UserDTO;
import com.adamkorzeniak.user.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	private SecureRandom random = new SecureRandom();

	public void register(UserDTO userForm) {

		User user = new User();
		user.setUsername(userForm.getUsername());
		user.setEmail(userForm.getEmail());
		user.setConfirmed(false);
		AccountConfirmation confirmation = new AccountConfirmation();
		confirmation.setToken(getToken());
		confirmation.setUser(user);
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime validTo = now.plusDays(1);
		confirmation.setValidTo(validTo);
		user.setAccountConfirmation(confirmation);
		String salthash = encoder.encode(userForm.getPassword());
		user.setSalthash(salthash);

		userRepository.save(user);
	}
	
	private String getToken() {
        return new BigInteger(130, random).toString(32);
	}

	@Override
	public boolean login(UserDTO userForm) {
		String storedhash = userRepository.findUsername(userForm.getUsername());
		return encoder.matches(userForm.getPassword(), storedhash);
	}

	@Override
	@Transactional
	public String confirm(String token) {
		User user = userRepository.findByConfirmationToken(token);
		if (user == null) {
			return null;
		}
		AccountConfirmation ac = user.getAccountConfirmation();
		Long id = ac.getId();
		userRepository.deleteConfirmation(id);
		if (ac.getValidTo().isBefore(LocalDateTime.now())) {
			return "";
		}
		user.setConfirmed(true);
		return user.getUsername();
	}

	@Override
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}
}
