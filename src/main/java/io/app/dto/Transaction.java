package io.app.dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Transaction {
	
	@Id
	@GenericGenerator(name = "transactionIdGen", strategy = "io.app.generator.TransactionIdGenerator")
	//@GenericGenerator(name = "transactionIdGen", strategy = "increment")
	@GeneratedValue(generator="transactionIdGen")
	private String transactionId;
	private double transactionAmount;
	@Temporal(TemporalType.DATE)
	private Date transactionDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	private double theDueAfterPayment;
	private  double theTotalDue;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="account_number_fk")
	private Account account;
	
	
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactionAmount=" + transactionAmount
				+ ", transactionDate=" + transactionDate + ", theDueAfterPayment=" + theDueAfterPayment
				+ ", theTotalDue=" + theTotalDue + ", account=" + account + "]";
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public double getTheTotalDue() {
		return theTotalDue;
	}
	public void setTheTotalDue(double theTotalDue) {
		this.theTotalDue = theTotalDue;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public double getTheDueAfterPayment() {
		return theDueAfterPayment;
	}
	public void setTheDueAfterPayment(double theDueAfterPayment) {
		this.theDueAfterPayment = theDueAfterPayment;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
