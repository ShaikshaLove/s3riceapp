package io.app.controller;


import io.app.controller.view.TransactionPdfView;
import io.app.util.TransactionList;
import io.app.util.TransactionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import io.app.dto.Account;
import io.app.dto.Transaction;
import io.app.event.OnTransactionComplete;
import io.app.service.IAccountService;
import io.app.service.ITransationService;

import javax.xml.crypto.Data;
import java.util.List;

@Controller
public class TransactionController {

	@Autowired
	private TransactionUtil transactionUtil;

	@Autowired
	private  ITransationService transationService;
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@RequestMapping(value="/doTransaction",method=RequestMethod.POST)
	public String saveTransaction(@RequestParam("accountNumber")long accountNumber,@ModelAttribute Transaction transaction,ModelMap map) {
		System.out.println(transaction);
		Account account=accountService.getAccountDetail(accountNumber);
		double theTotalDue=account.getTheTotalDue();
		double transactionAmount=transaction.getTransactionAmount();
		double theDueAfterPayment=theTotalDue-transactionAmount;
		transaction.setTheTotalDue(theTotalDue);
		transaction.setTheDueAfterPayment(theDueAfterPayment);
		account.setTheTotalDue(theDueAfterPayment);
		transaction.setAccount(account);
		transaction=transationService.save(transaction);
		applicationEventPublisher.publishEvent(new OnTransactionComplete(transaction));
		map.put("message"," The transaction has been completed with transaction Id "+transaction.getTransactionId());
		return "redirect:/getAccountDetails?accountNumber="+accountNumber;
	}
	
	@RequestMapping("/getTrxnPage")
	public String trxPage() {
		return "TransactionById";
	}
	
	@RequestMapping(value="/getTrxnById",method=RequestMethod.GET)
	public String getTransactionById(@RequestParam("transactionId")String transactionId,ModelMap map) {
		map.put("transaction", transationService.getTransactionById(transactionId));
		return "TransactionById";
	}
	
	@RequestMapping("/transactionsByDate")
	public ModelAndView getTransactionByDate(@RequestParam("trxDate")String trxDate){
		System.out.println("--------------------------- "+trxDate);

		TransactionList trxList=new TransactionList(transationService.getTransactionsByDate(trxDate),trxDate);

		System.out.println(trxList.getTransactions()+"--------------------- "+trxDate);
		return new ModelAndView("Transactions-By-Date","trxList",trxList);
	}


	@RequestMapping(value="/transactions",method = RequestMethod.GET)
	public String getTransacationPage(){
		return "Transactions-By-Date";
	}


	@RequestMapping(value="/transactions/pdf",method = RequestMethod.GET)
	public ModelAndView generatePdfForTransactions(@RequestParam(value="trxDate",required = false)String trxDate){
		TransactionList trxList;
		if(trxDate!=null)
			trxList=new TransactionList(transationService.getTransactionsByDate(trxDate),trxDate);
		   else
		  trxList=transactionUtil.getUiDynamics();
      return new ModelAndView(new TransactionPdfView(),"trxList",trxList);
	}
}
