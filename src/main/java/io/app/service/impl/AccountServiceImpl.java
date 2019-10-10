package io.app.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.app.dto.Account;
import io.app.exception.WrongAccountNumberException;
import io.app.repository.AccountRepository;
import io.app.service.IAccountService;
@Service
public class AccountServiceImpl implements IAccountService {
	
	@Autowired
    private AccountRepository accountRepository;
	
	@Override
	public Account createAccount(Account account) {
		account.setTheLastTrxnDate(new Date());
		return accountRepository.save(account);
	}

	@Override
	public Account getAccountDetail(long accountNumber) {
		return accountRepository.findById(accountNumber).orElseThrow(()->new WrongAccountNumberException("The Account number "+accountNumber+" is wrong "));
	}

	@Override
	public void deleteAccountDetail(long accountNumber) {
		accountRepository.deleteById(accountNumber);
	}
}
