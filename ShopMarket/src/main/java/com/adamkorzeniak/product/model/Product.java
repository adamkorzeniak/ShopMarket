package com.adamkorzeniak.product.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.adamkorzeniak.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String name;

	private String description;

	@Min(0)
	private Double price;
	
	private String imageUrl;
	
	@JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
	private User user;
    
    @NotNull
    @Transient
    private String ownerUsername;
    
    @NotNull
    @Transient
    private Boolean selfOwned;

	public String getDescription() {
		return description;
	}

	public Long getId() {
		return id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public User getUser() {
		return user;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOwnerUsername() {
		return ownerUsername;
	}

	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}

	public boolean isSelfOwned() {
		return selfOwned;
	}

	public void setSelfOwned(boolean selfOwned) {
		this.selfOwned = selfOwned;
	}
}
