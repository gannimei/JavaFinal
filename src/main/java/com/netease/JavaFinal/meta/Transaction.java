package com.netease.JavaFinal.meta;

public class Transaction {

	private int id;
	private int contentId;
	private int personId;
	private int price;
	private long time;
	private int number;
	private String title;
	private byte[] image;
	
	public Transaction() {}
	
	public Transaction(Shopping shopping) {
		this.contentId = shopping.getContentId();
		this.personId = shopping.getPersonId();
		this.price = shopping.getPrice();
		this.number = shopping.getNumber();
		this.time = System.currentTimeMillis();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return new String(image);
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public int getTotal() {
		return price * number;
	}

}
