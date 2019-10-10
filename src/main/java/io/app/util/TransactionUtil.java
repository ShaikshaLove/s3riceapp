package io.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.app.service.ITransationService;

@Component
public class TransactionUtil {
	
	@Autowired
    private ITransationService transactionService;
	
	public TransactionUtil() {
	}
	
	public TransactionList getUiDynamics(){
		String trxDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		return new TransactionList(transactionService.getTransactionsByDate(trxDate),trxDate);
	}

}
