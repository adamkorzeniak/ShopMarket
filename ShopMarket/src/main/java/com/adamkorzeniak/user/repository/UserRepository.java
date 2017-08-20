package com.adamkorzeniak.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adamkorzeniak.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	User findByUsernameAndSalthash(String username, String salthash);

	@Query("Select u.salthash from User u where u.username = ?1")
	String findUsername(String login);

	@Query("Select u from User u where u.accountConfirmation.token = ?1")
	User findByConfirmationToken(String token);

	@Modifying
	@Query("Delete from AccountConfirmation ac where ac.id = ?1")
	void deleteConfirmation(Long id);
}
