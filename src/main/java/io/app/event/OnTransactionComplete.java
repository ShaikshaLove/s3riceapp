package io.app.event;


import org.springframework.context.ApplicationEvent;

import io.app.dto.Transaction;

public class OnTransactionComplete extends ApplicationEvent {
	
     private Transaction transaction;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OnTransactionComplete(Transaction transaction) {
		super(transaction);
		this.transaction=transaction;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
