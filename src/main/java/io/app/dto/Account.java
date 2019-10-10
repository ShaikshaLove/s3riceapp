package io.app.dto;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
@Entity
public class Account {
	
	@Id
	@GenericGenerator(name = "accounIdGen", strategy = "io.app.generator.AccountIdGenerator")
	//@GenericGenerator(name = "accounIdGen", strategy = "increment")
	@GeneratedValue(generator="accounIdGen")
	private long accountNumber;
	private double theTotalDue;
	@Temporal(TemporalType.TIMESTAMP)
	private Date theLastTrxnDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="account")
	private Set<Transaction> transactions;
		
	public Set<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}
	public Set<PurchaseOrder> getPurchaseOrders() {
		return purchaseOrders;
	}
	public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}


	@OneToMany(cascade=CascadeType.ALL,mappedBy="account")
	private Set<PurchaseOrder> purchaseOrders;

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", theTotalDue=" + theTotalDue + ", theLastTrxnDate="
				+ theLastTrxnDate + ", customer=" + customer + ", purchaseOrders=" + purchaseOrders + "]";
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getTheTotalDue() {
		return theTotalDue;
	}
	public void setTheTotalDue(double theTotalDue) {
		this.theTotalDue = theTotalDue;
	}
	public Date getTheLastTrxnDate() {
		return theLastTrxnDate;
	}
	public void setTheLastTrxnDate(Date theLastTrxnDate) {
		this.theLastTrxnDate = theLastTrxnDate;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
