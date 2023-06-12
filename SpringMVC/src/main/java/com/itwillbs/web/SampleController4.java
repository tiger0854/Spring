package com.itwillbs.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController4 {

	private static final Logger logger = LoggerFactory.getLogger(SampleController4.class);
	
	//http://localhost:8088/doD
	//http://localhost:8088/doD?data=itwill
	@RequestMapping(value = "/doD",method = RequestMethod.GET)
	public String doD(RedirectAttributes rttr /* Model model *//* @ModelAttribute("data") String data */) {
		logger.debug("doD() 메서드 호출!");
		
		// Model 객체
		// RedirectAttributes 객체
		
		// 공통점 : 정보 전달
		// 차이점 : 
		//    Model 객체 : 전달 방식 상관없이 사용가능, URI에 값이 표시, F5 (데이터 유지)
		//    RedirectAttributes 객체 : redirect방식일때만 사용가능, URI에 값이 표시X, F5 (데이터 유지X, 1회성)
		
		// @ModelAttribute : 내부적으로 Model객체 생성후 그안에 저장해서 사용
		
		
		//logger.debug("data : "+data);
		//model.addAttribute("data", "ITWILL");
		rttr.addFlashAttribute("data", "ITWILL2");
		// 데이터를 doE 주소(메서드)로 전달
		
		//return "/doE";
//		return "redirect:/doE"; // 가상주소의 변화O,화면 변화 O 
//		return "forward:/doE";	// 가상주소 변화X,화면 변화 O
		return "redirect:/doE";
	}
	
	//http://localhost:8088/doE
	// http://localhost:8088/doE?data=itwill
	@RequestMapping(value = "/doE",method = RequestMethod.GET)
	public String doE(@ModelAttribute("data") String data) {
		logger.debug("doE() 메서드 호출!");
		logger.debug("data : "+data);
		
		return "";
	}
	
	
}
