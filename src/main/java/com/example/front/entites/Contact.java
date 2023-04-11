package com.example.front.entites;

public class Contact {
	private Integer id;
	private String userName;
	private String subject;
	private String email;
	private String message;
	private String phone;
	
	public Contact() {
		super();
	}

	public Contact(Integer id, String userName, String subject, String email, String message, String phone) {
		super();
		this.id = id;
		this.userName = userName;
		this.subject = subject;
		this.email = email;
		this.message = message;
		this.phone = phone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}

