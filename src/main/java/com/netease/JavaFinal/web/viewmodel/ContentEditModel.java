package com.netease.JavaFinal.web.viewmodel;

import com.netease.JavaFinal.meta.Content;

public class ContentEditModel {

	private Integer id;
	private String title;
	private int price;
	private String summary;
	private String image;
	private String detail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Content AsModel() {
		Content content = new Content();
		if (id == null) {
			content.setId(0);
		} else {
			content.setId(id);
		}
		content.setTitle(title);
		content.setPrice(price);
		content.setImage(image.getBytes());
		content.setSummary(summary);
		content.setDetail(detail.getBytes());
		return content;
	}

}
