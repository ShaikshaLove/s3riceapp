package io.app.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class OrderDetail {
	
	@Id
	@GenericGenerator(name = "odId", strategy = "sequence")
	//@GenericGenerator(name = "odId", strategy = "increment")
	@GeneratedValue(generator="odId")
	private int id;
	private int quantity;
	private double cost;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="product_id_fk")
	private Product product;
	
	/*
	 * @OneToOne(cascade=CascadeType.ALL) private Product product;
	 */
	
	
	
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", quantity=" + quantity + ", cost=" + cost + ", product=" + product + "]";
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	/*
	 * public Product getProduct() { return product; } public void
	 * setProduct(Product product) { this.product = product; }
	 */
	
	

}
