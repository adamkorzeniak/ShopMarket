package com.adamkorzeniak.user.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.adamkorzeniak.product.model.Product;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Column(unique = true)
	private String username;

	@NotNull
	@Email
	private String email;

	@NotNull
	private String salthash;

	@NotNull
	private boolean confirmed;

	@OneToOne (mappedBy="user", cascade=CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn (name="user_Id")
	private AccountConfirmation accountConfirmation;
	
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "user_id")
    private List<Product> comments = new ArrayList<>();

	public AccountConfirmation getAccountConfirmation() {
		return accountConfirmation;
	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public String getSalthash() {
		return salthash;
	}

	public String getUsername() {
		return username;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setAccountConfirmation(AccountConfirmation accountConfirmation) {
		this.accountConfirmation = accountConfirmation;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setSalthash(String salthash) {
		this.salthash = salthash;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public List<Product> getComments() {
		return comments;
	}

	public void setComments(List<Product> comments) {
		this.comments = comments;
	}
}
