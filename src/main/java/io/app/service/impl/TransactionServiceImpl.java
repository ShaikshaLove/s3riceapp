package io.app.service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.app.dto.Transaction;
import io.app.repository.TransactionRepository;
import io.app.service.ITransationService;
@Service
public  class TransactionServiceImpl implements ITransationService{
    
	@Autowired
	private TransactionRepository transactionRepository;
	
	
	@Override
	public Transaction save(Transaction transaction) {
		transaction.setTransactionDate(new Date());
		transaction.setTimestamp(new Date());
		return transactionRepository.save(transaction);
	}


	@Override
	public Transaction getTransactionById(String transactionId) {
		return transactionRepository.getOne(transactionId);
	}


	@Override
	public List<Transaction> getTransactionsByDate(String date) {
           SimpleDateFormat sdt=new SimpleDateFormat("yyyy-MM-dd");
           Date tdate = null;
		   try {
			     tdate = sdt.parse(date);
		       } catch (ParseException e) {
			       e.printStackTrace();
		       }         
		     return transactionRepository.findByTransactionDate(tdate);
	}

}
