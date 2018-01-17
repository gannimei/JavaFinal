package com.netease.JavaFinal.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.netease.JavaFinal.dao.ShoppingDao;
import com.netease.JavaFinal.dao.TransactionDao;
import com.netease.JavaFinal.meta.Shopping;
import com.netease.JavaFinal.meta.Transaction;

@Repository
public class TransactionService {

	@Autowired
	private ShoppingDao shoppingDao;
	@Autowired
	private TransactionDao transactionDao;

	@Transactional
	public void SettleAccounts() {
		List<Shopping> shoppings = shoppingDao.GetAll();
		if (shoppings != null) {
			List<Transaction> transactions = new LinkedList<Transaction>();
			for (Shopping item : shoppings) {
				transactions.add(new Transaction(item));
			}
			transactionDao.InsertList(transactions);
			shoppingDao.DeleteAll();
		}
	}

}
