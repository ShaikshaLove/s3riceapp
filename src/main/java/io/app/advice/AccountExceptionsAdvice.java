package io.app.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import io.app.exception.WrongAccountNumberException;

@ControllerAdvice
public class AccountExceptionsAdvice {

	@ExceptionHandler(WrongAccountNumberException.class)
	public ModelAndView handle(WrongAccountNumberException ex) {
		System.out.println(ex.getMessage());
		return new ModelAndView("PayNow","msg",ex.getMessage());
	}

}
