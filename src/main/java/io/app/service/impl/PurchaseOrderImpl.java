package io.app.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.app.dto.PurchaseOrder;
import io.app.repository.PurchaseOrderRepository;
import io.app.service.IPurchaseOrderService;
@Service	
public class PurchaseOrderImpl implements IPurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Override
	public PurchaseOrder createOrder(PurchaseOrder purchaseOrder) {
		purchaseOrder.setOrderedDate(new Date());
		return purchaseOrderRepository.save(purchaseOrder);
	}

	@Override
	public PurchaseOrder getPuchaseOrder(String orderId) {
		return purchaseOrderRepository.getOne(orderId);
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrders() {
		return purchaseOrderRepository.findAll();
	}

}
