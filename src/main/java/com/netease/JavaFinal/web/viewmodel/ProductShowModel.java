package com.netease.JavaFinal.web.viewmodel;

import com.netease.JavaFinal.dao.TransactionDao;
import com.netease.JavaFinal.meta.Content;
import com.netease.JavaFinal.meta.Person;
import com.netease.JavaFinal.meta.Transaction;
import com.netease.JavaFinal.utils.Springfactory;

public class ProductShowModel {

	private int id;
	private String title;
	private String image;
	private String summary;
	private String detail;
	private int price;
	private int buyPrice;
	private int buyNum;
	private int saleNum;
	private boolean isBuy;
	private boolean isSell;

	public ProductShowModel(Content content, Person user) {
		this.id = content.getId();
		this.title = content.getTitle();
		this.image = new String(content.getImage());
		this.summary = content.getSummary();
		this.detail = new String(content.getDetail());
		this.price = content.getPrice();
		TransactionDao transactionDao = Springfactory.getBean("transactionDao");
		Transaction trx = transactionDao.GetByContentId(content.getId());
		if (user != null) {
			if (user.getUserType() == 0) {
				if (trx == null) {
					isBuy = false;
				} else {
					isBuy = true;
					buyPrice = trx.getPrice();
					buyNum = trx.getNumber();
				}
			} else {
				if (trx == null) {
					isSell = false;
				} else {
					isSell = true;
					saleNum = trx.getNumber();
				}
			}
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public int getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(int saleNum) {
		this.saleNum = saleNum;
	}

	public boolean isBuy() {
		return isBuy;
	}

	public void setBuy(boolean isBuy) {
		this.isBuy = isBuy;
	}

	public boolean isSell() {
		return isSell;
	}

	public void setSell(boolean isSell) {
		this.isSell = isSell;
	}
	
}
