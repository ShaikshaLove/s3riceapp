/**
 * 
 */
package io.app.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author shaiksha
 *
 */
@ControllerAdvice
public class MyExceptionHandler  {

	@ExceptionHandler(Exception.class)
	public String errorHandler(Exception e) {
		return "CommonError";
	}
	
}
