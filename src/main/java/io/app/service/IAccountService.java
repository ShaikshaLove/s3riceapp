package io.app.service;



import io.app.dto.Account;

public interface IAccountService{
	
	public Account createAccount(Account account);
	public Account getAccountDetail(long accountNumber);
	public void deleteAccountDetail(long accountNumber);

}
