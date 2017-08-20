package com.adamkorzeniak.user.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AccountConfirmation {
	
	@Id @GeneratedValue
	private Long id;
	
	private String token;
	
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private LocalDateTime validTo;

	public Long getId() {
		return id;
	}
	
	public String getToken() {
		return token;
	}
	
	public LocalDateTime getValidTo() {
		return validTo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setValidTo(LocalDateTime validTo) {
		this.validTo = validTo;
	}
}