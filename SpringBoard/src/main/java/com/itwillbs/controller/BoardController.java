package com.itwillbs.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

@Controller
@RequestMapping(value = "/board/*")     //특정 주소에 따른 컨트롤러 구분
public class BoardController {

	// 서비스 객체 주입
	@Autowired
	private BoardService service;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	
	// 글쓰기 - /board/regist  (GET)
	// http://localhost:8088/board/regist
	@RequestMapping(value = "/regist",method = RequestMethod.GET)
	public void registGET() throws Exception{
		logger.debug(" registGET() 호출! ");
		logger.debug(" /board/regist.jsp 페이지 이동 ");
	}
	
	// 글쓰기 - /board/regist  (POST)
	@RequestMapping(value = "/regist",method = RequestMethod.POST)
	public String registPOST(BoardVO vo,RedirectAttributes rttr) throws Exception{
		logger.debug(" registPOST() 호출 ");
		// 한글처리(필터)
		// 페이지 전달된 데이터(파라메터) 저장
		//logger.debug("vo :"+vo);
		logger.debug("vo : {}",vo); //err 레벨에서 사용권장
		//logger.error(msg);
		
		// 서비스 - 글쓰기 동작 호출
		service.insertBoard(vo);
		
		// 리스트로 정보를 전달 (rttr)
		rttr.addFlashAttribute("result", "CREATEOK");
		
		// 리스트 페이지로 이동
		//return "/board/success";
		//return "redirect:/board/listALL?test=12345"; //Model객체(@ModelAttrbute)
		return "redirect:/board/listALL";
	}
	
	// http://localhost:8088/board/listALL
	// 게시판 글 목록
	//@RequestMapping(value = "/listALL",method = {RequestMethod.GET,RequestMethod.POST} )
	@RequestMapping(value = "/listALL",method = RequestMethod.GET )
	public String listALLGET(HttpSession session,Model model,@ModelAttribute("result") String result) throws Exception{
		logger.debug(" listALLGET() 호출 ");
		logger.debug(" result : "+result);
		
		// 서비스 - DB에 저장된 글 정보를 가져오기
		List<BoardVO> boardList = service.getListAll();
		logger.debug("boardList : "+boardList);
		
		// 조회수 체크 값
		session.setAttribute("checkViewCnt", true);
		
		// 연결된 뷰페이지로 전달 (뷰-출력)
		model.addAttribute("boardList", boardList);
		
		return "/board/listALL";
	}
	

	// http://localhost:8088/board/read?bno=6
	// 글 내용(본문)보기
	@RequestMapping(value = "/read",method = RequestMethod.GET)
	public void readGET(Model model,HttpSession session,@RequestParam("bno") int bno/* ,@ModelAttribute BoardVO vo */) throws Exception{
		// @RequestParam => getParameter() , 1:1 매핑 , 자동으로 타입케스팅(형변환)
		// @ModelAttribute =>  getParameter() + Model, 1:N 매핑
		logger.debug(" readGET() 호출 ");
		
		// 전달정보 저장(bno)
		logger.debug(" bno : "+bno);
//		logger.debug("vo : "+vo.getBno());
		
		boolean checkValue = (Boolean)session.getAttribute("checkViewCnt");
		
		if(checkValue) {
			// 조회수 1증가	 (checkViewCnt 정보가 참일때만)
			// => 서비스 동작 호출 
			service.upViewcnt(bno);
			session.setAttribute("checkViewCnt", false);
		}
		
		// 글정보 조회(특정글)
		service.getBoard(bno);
		// 글정보를 Model 저장 => 연결된 뷰페이지로 전달
		model.addAttribute("vo",service.getBoard(bno));
		
		// 뷰페이지로 이동	(/board/read.jsp)	
	}
	
	
	
	
	
	
	
}// controller
