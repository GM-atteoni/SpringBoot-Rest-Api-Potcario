package com.pot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pot.model.User;

@Deprecated
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmailAndPwd(String pEmail, String pPwd);

	public User findByEmail(String pEmail);
}
