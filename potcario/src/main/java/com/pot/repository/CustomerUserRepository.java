package com.pot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pot.model.CustomerUser;

@Repository
public interface CustomerUserRepository extends JpaRepository<CustomerUser, Integer> {

}
