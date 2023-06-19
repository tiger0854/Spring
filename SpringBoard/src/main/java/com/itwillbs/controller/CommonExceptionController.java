package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionController {

	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionController.class);
	
	// http://localhost:8088/board/read?bno=A
	@ExceptionHandler(Exception.class)
	public void common( ) {
		logger.debug(" 예외발생!!! ");
	}
	
	
}
