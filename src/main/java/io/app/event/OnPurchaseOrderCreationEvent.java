package io.app.event;

import org.springframework.context.ApplicationEvent;

import io.app.dto.PurchaseOrder;

public class OnPurchaseOrderCreationEvent extends ApplicationEvent {

	
	private PurchaseOrder purchaseOrder;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OnPurchaseOrderCreationEvent(PurchaseOrder purchaseOrder) {
		super(purchaseOrder);
		this.purchaseOrder=purchaseOrder;	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

}
