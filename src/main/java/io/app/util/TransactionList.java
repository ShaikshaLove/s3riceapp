package io.app.util;

import java.util.Date;
import java.util.List;

import io.app.dto.Transaction;

public class TransactionList {
	private double totalTrxAmount;
    private List<Transaction> transactions;
    private String trxDate;

	public TransactionList(List<Transaction> transactions, String trxDate) {
		this.transactions = transactions;
		this.trxDate = trxDate;
	}

	public String getTrxDate() {
		return trxDate;
	}

	public void setTrxDate(String trxDate) {
		this.trxDate = trxDate;
	}

	public TransactionList(List<Transaction> transactions) {
		this.transactions=transactions;
	}


	private double getTrxAmount() {
		double amount=0.0;
		for(Transaction trx:transactions) {
			amount=amount+trx.getTransactionAmount();
		}
      return amount;		
	}


	public double getTotalTrxAmount() {
		totalTrxAmount=getTrxAmount();
		return totalTrxAmount;
	}


	public void setTotalTrxAmount(double totalTrxAmount) {
		this.totalTrxAmount = totalTrxAmount;
	}


	public List<Transaction> getTransactions() {
		return transactions;
	}


	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public TransactionList() {
	}
}
	