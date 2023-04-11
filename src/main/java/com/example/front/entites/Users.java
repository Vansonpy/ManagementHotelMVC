package com.example.front.entites;

import java.util.Set;

public class Users {
	private Integer id;
	private String firstName;
	private String lastName;
	private String fullName;
	private String userName;
	private String password;
	private String email;
	private String phone;
	private Byte gender;
	private String confirmPassword;
	private String role;
	private Set<Sales> sales;

	public Users() {
		super();
	}

	public Users(String firstName, String lastName, String fullName, String userName, String password, String email,
			String phone, Byte gender, String confirmPassword, String role, Set<Sales> sales) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = fullName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.confirmPassword = confirmPassword;
		this.role = role;
		this.sales = sales;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Byte getGender() {
		return gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Sales> getSales() {
		return sales;
	}

	public void setSales(Set<Sales> sales) {
		this.sales = sales;
	}

	

}

