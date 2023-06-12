package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// @Controller => 컨트롤러의 역할 수행  (servlet-context.xml 객체 등록)
//	  * 컨트롤러 (Controller)
//	    0) doGet/doPost => doProcess
//	    1) 가상주소 계산
//	    2) 가상주소 비교
//	    3) 페이지 이동

@Controller
public class SampleController1 {

	private static final Logger logger = LoggerFactory.getLogger(SampleController1.class);
	
	// http://localhost:8088/프로젝트명/ (model2)
	// http://localhost:8088/top-level 패키지명/ (springMVC) 
	
	//컨트롤러에서 필요한 동작을 메서드 
	//@RequestMapping(value = "매핑할 주소",method = 전달방식(get/post) )
	
	// http://localhost:8088/web/  (시작주소)
	// http://localhost:8088/web/doA
	// http://localhost:8088/web/doABC
	
	// 메시지 파일 [/WEB-INF/views/doA.jsp]을(를) 찾을 수 없습니다.
	
	//  메서드 리턴타입 void 
	//   => /WEB-INF/views/[주소].jsp
	@RequestMapping(value = "/doA", method = RequestMethod.GET)
	public void doA() {
		logger.debug(" doA() 실행 ");
	}
	
	// // http://localhost:8088/web/doA1
	// doA1 주소로 호출되는 메서드 doA1() 실행 
	//@RequestMapping("/doA1")
	@GetMapping(value = "/doA1")
	public void doA1() {
		logger.debug(" doA1() 실행 !!! ");
		
	}
	
	
	
	

}
