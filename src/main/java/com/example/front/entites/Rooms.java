package com.example.front.entites;

import java.util.Set;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.multipart.MultipartFile;

@EntityScan
public class Rooms {
	private Integer id;
	private String roomName;
	private String type;
	private Integer price;
	private Set<Sales> sales;
	private String urlImage;
	private MultipartFile multipartFile;
	public Rooms() {
		super();
	}
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	public Rooms(Integer id, String roomName, String type, Integer price, Set<Sales> sales, String urlImage,
			MultipartFile multipartFile) {
		super();
		this.id = id;
		this.roomName = roomName;
		this.type = type;
		this.price = price;
		this.sales = sales;
		this.urlImage = urlImage;
		this.multipartFile = multipartFile;
	}

	public Rooms(Integer id, String roomName, String type, Integer price, Set<Sales> sales, MultipartFile fileImage,
			String urlImage) {
		super();
		this.id = id;
		this.roomName = roomName;
		this.type = type;
		this.price = price;
		this.sales = sales;
		this.urlImage = urlImage;
	}


	public String getUrlImage() {
		return urlImage;
	}



	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Set<Sales> getSales() {
		return sales;
	}

	public void setSales(Set<Sales> sales) {
		this.sales = sales;
	}

}
