package com.pot.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="customeruser")
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

	
	
}
