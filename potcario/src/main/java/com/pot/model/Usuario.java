package com.pot.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="customeruser")
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer customeruserid;
	
	@NotNull
	private String name;
	
	@NotNull
	private String email;
	
	@NotNull
	private String pwd;
	
	@NotNull
	private String adressstreet;
	
	@NotNull
	private Integer adressnumber;
	
	@NotNull
	private String city;
	
	@NotNull
	private String country;
	
	@NotNull
	@CreatedDate
	private Date creationdate;
	
	@NotNull
	@LastModifiedDate
	private Date updatedate;

	public Integer getCustomeruserid() {
		return customeruserid;
	}

	public void setCustomeruserid(Integer customeruserid) {
		this.customeruserid = customeruserid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAdressstreet() {
		return adressstreet;
	}

	public void setAdressstreet(String adressstreet) {
		this.adressstreet = adressstreet;
	}

	public Integer getAdressnumber() {
		return adressnumber;
	}

	public void setAdressnumber(Integer adressnumber) {
		this.adressnumber = adressnumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	
	
}
