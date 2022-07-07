package com.john.graphite.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "myUser")
// , uniqueConstraints = @UniqueConstraint(columnNames = {"phone_no",
// "mail"}))
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private int id;
//	@NotBlank(message = "username is mandatory")
	private String username;
	@Column(name = "mail", unique = true)
//	@NotBlank(message = "email is mandatory")
//	@Email(message = "email should be valid")
	private String email;
//	@NotBlank(message = "phone_no is mandatory")
	@Column(name = "phone_no", unique = true)
	private String phoneNo;
	@Column(name = "created_by")
	private String createdBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "created_on")
	// @PastOrPresent
	private Date createdOn;
	@Column(name = "updated_by")
	private String updatedBy;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "updated_on")
	// @FutureOrPresent
	private LocalDate updatedOn;

	public User() {
		super();
	}

	public User(int id, String username, String email, String phoneNo, String createdBy, Date createdOn) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.phoneNo = phoneNo;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	public User(String username, String email, String phoneNo) {
		super();
		this.username = username;
		this.email = email;
		this.phoneNo = phoneNo;

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	static int i=1;
	
	public void setId() {
		this.id = i++;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDate getUpdatedOn() {
		return updatedOn;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUpdatedOn(LocalDate updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", phoneNo=" + phoneNo
				+ ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", updatedOn="
				+ updatedOn + "]";
	}

}
