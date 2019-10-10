package io.app.service;

import java.util.List;

import io.app.dto.PurchaseOrder;

public interface IPurchaseOrderService {
   public PurchaseOrder createOrder(PurchaseOrder purchaseOrder);
   public PurchaseOrder  getPuchaseOrder(String orderId);
   public List<PurchaseOrder> getAllPurchaseOrders();
}
