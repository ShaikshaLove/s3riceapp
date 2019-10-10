package io.app.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.app.dto.Account;
import io.app.dto.OrderDetail;
import io.app.dto.PurchaseOrder;
import io.app.event.OnPurchaseOrderCreationEvent;
import io.app.service.IAccountService;
import io.app.service.IProductService;
import io.app.service.IPurchaseOrderService;
import io.app.util.OrderUtil;
@Controller
public class PurchaseOrderController {

	@Autowired
	private IAccountService accountService;

	@Autowired
	private OrderUtil orderUtil;

	@Autowired
	private IProductService productService;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private IPurchaseOrderService purchaseOrderService;

	@RequestMapping(value="/getAccounDetailsForOrder",method=RequestMethod.GET)
	public String getOrderForm(@RequestParam("accountNumber")long accountNumber,ModelMap map,HttpServletRequest request) {
		HttpSession session=request.getSession(true);
		@SuppressWarnings("unchecked")
		Set<OrderDetail> orderDetails=(Set<OrderDetail>)session.getAttribute("orderDetails");
		map.put("orderDetail", new OrderDetail());
		map.put("products",orderUtil.getDynamics() );
		map.put("account", accountService.getAccountDetail(accountNumber));
		map.put("orderDetails", orderDetails);
		return "OrderRegister";
	}

	@RequestMapping("/regOrder")
	public String getRegOrder() {
		return "OrderRegister";
	}

	@RequestMapping("/addProduct")
	public String addProduct(@RequestParam("accountNumber")long accountNumber,@ModelAttribute OrderDetail orderDetail,HttpServletRequest request) {
		orderDetail.setProduct(productService.getProduct(orderDetail.getProduct().getProductId()));
		orderDetail.setCost(orderDetail.getQuantity()*orderDetail.getProduct().getCurrentCost());
		HttpSession session=request.getSession(true);
		@SuppressWarnings("unchecked")
		Set<OrderDetail> orderDetails=(Set<OrderDetail>)session.getAttribute("orderDetails");
		if(orderDetails!=null) {
			orderDetails.add(orderDetail);
			System.out.println(orderDetails);
		}else {
			Set<OrderDetail> orderDet=new HashSet<>();
			orderDet.add(orderDetail);
			session.setAttribute("orderDetails", orderDet);
			System.out.println(orderDet);
		}
		return "redirect:/getAccounDetailsForOrder?accountNumber="+accountNumber;
	}
	
	@RequestMapping("/conformOrder")
	public String conformOrder(@RequestParam("accountNumber")long accountNumber,HttpServletRequest request,ModelMap map) {
		HttpSession session=request.getSession(false);
		@SuppressWarnings("unchecked")
		Set<OrderDetail> orderDetails=(Set<OrderDetail>)session.getAttribute("orderDetails");
		double orderAmount=0.0;
	    for(OrderDetail od:orderDetails) {
	    	orderAmount=orderAmount+od.getCost();
	    	od.setProduct(productService.getProduct(od.getProduct().getProductId()));
	    }
		PurchaseOrder purchaseOrder=new PurchaseOrder();
		purchaseOrder.setOrderAmount(orderAmount);
		purchaseOrder.setOrderdetails(orderDetails);
		Account account=accountService.getAccountDetail(accountNumber);
		account.setTheTotalDue((account.getTheTotalDue()+orderAmount));
		

		purchaseOrder.setAccount(account);
		purchaseOrder=purchaseOrderService.createOrder(purchaseOrder);
		// firing event
		eventPublisher.publishEvent(new OnPurchaseOrderCreationEvent(purchaseOrder));
		
		session.setAttribute("orderDetails", null);
		map.put("message", "Voila! The Order has been placed with order id "+purchaseOrder.getOrderId());
		return "OrderRegister";
	}
	
	
	@RequestMapping("/getOrderById")
	public String getOrderDetails(@RequestParam("orderId")String orderId,ModelMap map) {
		map.put("purchaseOrder",purchaseOrderService.getPuchaseOrder(orderId));
		return "OrderDetail";
	}
	
	@RequestMapping("/getOrder")
	public String getOrderDetailPage() {
		return "OrderDetail";
	}
}
