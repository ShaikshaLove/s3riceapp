package io.app.dto;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="purchase_order")
public class PurchaseOrder {
	
	@Id
    @GenericGenerator(name = "orderIdGen", strategy = "io.app.generator.OrderIdGenerator")
//	@GenericGenerator(name = "orderIdGen", strategy = "increment")

	@GeneratedValue(generator="orderIdGen")
	private String orderId;
	
	private double orderAmount;
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderedDate;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "account_number")
	private Account account;
	
	public Date getOrderedDate() {
		return orderedDate;
	}
	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}
	public Set<OrderDetail> getOrderdetails() {
		return orderdetails;
	}
	public void setOrderdetails(Set<OrderDetail> orderdetails) {
		this.orderdetails = orderdetails;
	}
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumns(value = { @JoinColumn(name="order_id_fk") })
	private Set<OrderDetail> orderdetails;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	

}
