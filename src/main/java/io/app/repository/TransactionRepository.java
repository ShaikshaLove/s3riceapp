package io.app.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.app.dto.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
     List<Transaction> findByTransactionDate(Date transactionDate);
}
