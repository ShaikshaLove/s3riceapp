package io.app.event;

import org.jboss.logging.Logger;
import org.springframework.context.ApplicationEvent;

import io.app.dto.Account;

public class OnAccountCreationCompleteEvent extends ApplicationEvent {
	
	private Logger logger=Logger.getLogger(OnAccountCreationCompleteEvent.class);

	private Account account;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OnAccountCreationCompleteEvent(Account account) {
		super(account);
		this.account=account;
		logger.info("OneAccountCreationComplteEvent is fired");
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	

}
