package io.app.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EntityNotFoundExceptionHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public void entityNotFoundExceptionHandler() {
		
		
		
	}

}
