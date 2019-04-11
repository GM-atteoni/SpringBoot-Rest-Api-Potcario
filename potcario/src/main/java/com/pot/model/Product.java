package com.pot.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="product")
@EntityListeners(AuditingEntityListener.class)
public class Product {

	@Id
	private Integer productid;
	
	@NotNull
	private Integer adminuserid;
	
	@NotNull
	private String name;
	
	@NotNull
	private String description;
	
	@NotNull
	@CreatedDate
	private Date creationdate;
	
	@NotNull
	@LastModifiedDate
	private Date updatedate;

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public Integer getAdminuserid() {
		return adminuserid;
	}

	public void setAdminuserid(Integer adminuserid) {
		this.adminuserid = adminuserid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
