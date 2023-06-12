package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/itwill")
public class SampleController2 {

	private static final Logger logger 
	           = LoggerFactory.getLogger(SampleController2.class);
	
	// String 타입 리턴 : 
	//  => /WEB-INF/views/[리턴된 문자열].jsp
	
	// http://localhost:8088/web/doB
	// http://localhost:8088/web/itwill/doB
	// http://localhost:8088/itwill/doB
	@RequestMapping(value = "/doB",method = RequestMethod.GET)
	public String doB() {
		logger.debug(" doB() 호출!! ");
		
		logger.debug(" 연결된 뷰페이지로 이동 (컨트롤러 자동 처리)");
		return "/itwill/string";
	}
	// http://localhost:8088/web/doB1
	@RequestMapping(value = "/doB1",method = RequestMethod.GET)
	public String doB1() {
		logger.debug(" doB1() 호출!! ");
		
		logger.debug(" 연결된 뷰페이지로 이동 (컨트롤러 자동 처리)");
		return "string";
	}
	
	
	
	

}
