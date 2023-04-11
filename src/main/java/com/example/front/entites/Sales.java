package com.example.front.entites;

import java.util.Date;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Sales {
	private Integer id;
	private String roomName;
	private Date checkinDate;
	private Date checkoutDate;
	private Integer status;
	private String userName;
	private Integer price;
	private String type;
		
	public Sales() {
		super();
	}

	public Sales(Integer id, String roomName, Date checkinDate, Date checkoutDate, Integer status, String userName,
			Integer price, String type) {
		super();
		this.id = id;
		this.roomName = roomName;
		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
		this.status = status;
		this.userName = userName;
		this.price = price;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Date getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}

	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
