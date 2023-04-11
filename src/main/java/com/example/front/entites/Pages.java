package com.example.front.entites;

import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.multipart.MultipartFile;

@EntityScan
public class Pages {
	private Integer id;
	private String pageName;
	private String title;
	private String listContent;
	private String subContent;
	private MultipartFile multipartFile;
	private List<String> listUrlImage;
	public Pages() {
		super();
	}
	
	public Pages(Integer id, String pageName, String title, String listContent, String subContent,
			MultipartFile multipartFile, List<String> listUrlImage) {
		super();
		this.id = id;
		this.pageName = pageName;
		this.title = title;
		this.listContent = listContent;
		this.subContent = subContent;
		this.multipartFile = multipartFile;
		this.listUrlImage = listUrlImage;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	public List<String> getListUrlImage() {
		return listUrlImage;
	}
	public void setListUrlImage(List<String> listUrlImage) {
		this.listUrlImage = listUrlImage;
	}
	public void setListContent(String listContent) {
		this.listContent = listContent;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getListContent() {
		return listContent;
	}
	public void setContent(String listContent) {
		this.listContent = listContent;
	}
	public String getSubContent() {
		return subContent;
	}
	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}
	public List<String> listUrlImage() {
		return listUrlImage;
	}
	public void setListImg(List<String> listUrlImage) {
		this.listUrlImage = listUrlImage;
	}
	
	
}
