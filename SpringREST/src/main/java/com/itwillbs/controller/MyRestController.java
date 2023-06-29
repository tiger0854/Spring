package com.itwillbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.MemberVO;

// @RestController : REST방식의 처리하는 컨트롤러
@RestController
public class MyRestController {

	private static final Logger logger = LoggerFactory.getLogger(MyRestController.class);
	// http://localhost:8088/controller/
	
	// http://localhost:8088/controller/test1
	@RequestMapping(value = "/test1",method = RequestMethod.GET)
	public void test1() {
		// void리턴시 정보 전달 X
		logger.debug("test1() 호출");
	}
	
	// http://localhost:8088/controller/test2
	// http://localhost:8088/controller/test2?msg=10000
	@RequestMapping(value = "/test2",method = RequestMethod.GET)
	public String test2(String msg) {
		// String 리턴시 문자 데이터를 전달(의미)
		//  동작의 결과를 처리 (yes,no,ok...)
		logger.debug("test2() 호출");
		
		return "ITWILL : "+msg;
	}
	
	// http://localhost:8088/controller/test3
	// http://localhost:8088/controller/test3?name=itwill&tel=010-1234-1234
	@RequestMapping(value = "/test3",method = RequestMethod.POST)
	public /* @ResponseBody */ MemberVO test3(@RequestBody MemberVO vo) {
		logger.debug("test3() 호출");   // @RequestBody : JSON형태의 데이터를 객체형태로 전환(타입전환)
		// MemberVO(객체)를 리턴시  JSON타입으로 처리 
		// (+jackson-databind 라이브러리 추가)
		
//		MemberVO vo = new MemberVO();
//		vo.setName("홍길동");
//		vo.setTel("010-1234-1231");		
		
		return vo;
	}
	
	// http://localhost:8088/controller/test4
	@RequestMapping(value = "/test4",method = RequestMethod.GET)
	public List<MemberVO> test4() {
		// List형태의 데이터를 리턴 => JSON Array 형태로 데이터 생성
		logger.debug("test4() 호출");
		
		List<MemberVO> memberList 
		          = new ArrayList<MemberVO>();
		for(int i=0;i<10;i++) {
			MemberVO vo = new MemberVO();
			vo.setName("홍길동");
			vo.setTel("010-1234-1231");	
			
			memberList.add(vo);
		}
		
		//return service.getMemberList();
		return memberList;
	}
	// http://localhost:8088/controller/test5
	@RequestMapping(value = "/test5",method = RequestMethod.GET)
	public Map<Integer, MemberVO> test5() {
		// Map형태의 데이터를 리턴 => JSON Object 형태로 데이터 생성
		logger.debug("test5() 호출");
		
		Map<Integer, MemberVO> memberMap 
		      = new HashMap<Integer, MemberVO>();
		
		for(int i=0;i<10;i++) {
			MemberVO vo = new MemberVO();
			vo.setName("홍길동");
			vo.setTel("010-1234-1231");	
			
			memberMap.put(i, vo);
		}
		
		return memberMap;
	}
	

	// http://localhost:8088/controller/test6
	// http://localhost:8088/controller/600
	// http://localhost:8088/controller/numbers/600
	// http://localhost:8088/controller/numbers/605
	
	@RequestMapping(value = "/numbers/{num}",method = RequestMethod.GET)
	public String test6(@PathVariable("num") int num) {
		logger.debug(" test6() 호출 ");
		return " num : "+num;
	}
	
	// ResponseEntity 객체 : REST방식의 처리는 
	//  뷰페이지가 없음. 페이지의 상태를 알기 어려움
	// => 결과데이터 + HTTP 상태코드
	// http://localhost:8088/controller/test7
	@RequestMapping(value = "/test7",method = RequestMethod.GET)
	public ResponseEntity<Void> test7() {
		logger.debug("test7() 호출");
		
		//return new ResponseEntity<Void>(HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	// http://localhost:8088/controller/test8
	@RequestMapping(value = "/test8",method = RequestMethod.GET)
	public ResponseEntity<String> test8() {
		logger.debug("test8() 호출");
//		if() {
//			조건 만족  (데이터, 상태)
//		}else {
//			조건 불만족 (데이터, 상태)
//		}
		return new ResponseEntity<String>("ITWILL ERROR",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// http://localhost:8088/controller/test9
	@RequestMapping(value = "/test9",method = RequestMethod.GET)
	public ResponseEntity<MemberVO> test9() {
		logger.debug("test9() 호출");
		
		// 서비스 -> 메서드 호출결과 리턴
		MemberVO vo = new MemberVO();
		vo.setName("아이티윌");
		vo.setTel("051-803-0909");
		
		//ResponseEntity<List<MemberVO>> respEntity = null;
		//ResponseEntity<Map<String,Object>> respEntity = null;
		ResponseEntity<MemberVO> respEntity = null;
		if(vo != null) {
			respEntity = new ResponseEntity<MemberVO>(vo,HttpStatus.OK);
		}else {
			respEntity = new ResponseEntity<MemberVO>(vo,HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
//		return new ResponseEntity<MemberVO>(vo,HttpStatus.INTERNAL_SERVER_ERROR);
		return respEntity;
	}
	
	// http://localhost:8088/controller/test10
	@RequestMapping(value = "/test10",method = RequestMethod.GET)
	public ResponseEntity test10(){
		System.out.println(" test10() 호출 ");
		
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		String msg = "";
		   msg += "<script>";
		   msg += " alert('에러 발생!!!');";
		   //msg += " location.href='주소';";
		   msg += "</script>";
				
		return  new ResponseEntity(msg,respHeaders,HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
}
