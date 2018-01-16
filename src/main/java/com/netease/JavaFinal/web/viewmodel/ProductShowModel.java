package com.netease.JavaFinal.web.viewmodel;

import java.sql.Blob;

import com.netease.JavaFinal.dao.TransactionDao;
import com.netease.JavaFinal.meta.Content;
import com.netease.JavaFinal.meta.Person;
import com.netease.JavaFinal.meta.Transaction;
import com.netease.JavaFinal.utils.Springfactory;

public class ProductShowModel {

	public int id;
	public String title;
	public Blob image;
	public String summary;
	public Blob detail;
	public int price;
	public int buyPrice;
	public int buyNum;
	public int saleNum;
	public boolean isBuy;
	public boolean isSell;

	public ProductShowModel(Content content, Person user) {
		this.id = content.getId();
		this.title = content.getTitle();
		this.image = content.getImage();
		this.summary = content.getSummary();
		this.detail = content.getDetail();
		this.price = content.getPrice();
		TransactionDao transactionDao = Springfactory.getBean("transactionDao");
		if (user != null) {
			if (user.getUserType() == 0) {
				Transaction trx = transactionDao.GetByPersonId(user.getId());
				if (trx == null) {
					isBuy = false;
				} else {
					isBuy = true;
					buyPrice = trx.getPrice();
					buyNum = trx.getNumber();
				}
			} else {
				Transaction trx = transactionDao.GetByContentId(content.getId());
				if (trx == null) {
					isSell = false;
				} else {
					isSell = true;
					saleNum = trx.getNumber();
				}
			}
		}
	}

}
