package com.netease.JavaFinal.meta;

import java.sql.Blob;

public class Content {

	private int id;
	private int price;
	private String title;
	private Blob image;
	private String summary;
	private Blob detail;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Blob getDetail() {
		return detail;
	}

	public void setDetail(Blob detail) {
		this.detail = detail;
	}

}
