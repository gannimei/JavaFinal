package com.netease.JavaFinal.dao;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import com.netease.JavaFinal.meta.Transaction;

public class TransactionMapperProvider {

	public String insertAll(Map<String,List<Transaction>> map) {  
		List<Transaction> transactions = map.get("list");
		StringBuilder sb = new StringBuilder();  
        sb.append("insert into trx(contentId,personId,price,time,number) values");
        MessageFormat messageFormat = new MessageFormat("(#'{'list[{0}].contentId},#'{'list[{0}].personId},#'{'list[{0}].price},#'{'list[{0}].time},#'{'list[{0}].number})");
        for(int i = 0 ;i<transactions.size();i++) {  
            sb.append(messageFormat.format(new Object[]{i}));  
            if (i < transactions.size() - 1) {    
                sb.append(",");     
            }  
        }
        return sb.toString(); 
	}
	
}
