package io.app.service;

import java.util.List;
import io.app.dto.Transaction;
public interface ITransationService {
   public  Transaction save(Transaction transaction);
   public Transaction getTransactionById(String transactionId);
   public List<Transaction> getTransactionsByDate(String date);
}
