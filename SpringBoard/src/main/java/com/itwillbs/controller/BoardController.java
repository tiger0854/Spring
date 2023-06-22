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
import com.itwillbs.domain.PageMaker;
import com.itwillbs.domain.PageVO;
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
	// http://localhost:8088/board/listALL?page=2
	// http://localhost:8088/board/listALL?page=3&pageSize=20
	// 게시판 글 목록
	//@RequestMapping(value = "/listALL",method = {RequestMethod.GET,RequestMethod.POST} )
	@RequestMapping(value = "/listALL",method = RequestMethod.GET )
	public String listALLGET(PageVO vo,HttpSession session,Model model,@ModelAttribute("result") String result) throws Exception{
		logger.debug(" listALLGET() 호출 ");
		logger.debug(" result : "+result);
		
		//PageVO vo = new PageVO(); // page = 1, pageSize=10
		//vo.setPage(2);
		
		// 서비스 - DB에 저장된 글 정보를 가져오기
		//List<BoardVO> boardList = service.getListAll();
		// 서비스 - 페이징처리된 리스트정보 가져오기
		List<BoardVO> boardList = service.getBoardListPage(vo);
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
	public void readGET(Model model,
						HttpSession session,
						@RequestParam("bno") int bno/* ,@ModelAttribute BoardVO vo */) throws Exception{
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
		// 글정보를 Model 저장 => 연결된 뷰페이지로 전달
		model.addAttribute("vo", service.getBoard(bno));
		//model.addAttribute(service.getBoard(bno));
		// => 호출하는 이름 : boardVO
		//     전달하는 key(이름)이 없는 경우
		//     전달된는 객체의 타입의 첫글자를 소문자로 변경해서 이름으로 사용
		
		// 뷰페이지로 이동	(/board/read.jsp)	
	}
	
	
	// http://localhost:8088/board/modify?bno=6
	// 글 정보 수정(GET)
	@RequestMapping(value = "/modify",method = RequestMethod.GET)
	public void updateBoardGET(Model model, @RequestParam("bno") int bno ) throws Exception{
		logger.debug(" updateBoardGET() 호출 ");
		// 전달정보 저장(bno)
		logger.debug(" bno : "+bno);
		
		// 서비스 - 특정 글정보 가져오기
		// Model 저장해서 연결된 뷰페이지로 전달
		model.addAttribute("vo", service.getBoard(bno) ); 
		//  /board/modifiy.jsp (read.jsp 참조)
	}
	
	
	// 글 정보 수정(POST)
	@RequestMapping(value = "/modify",method = RequestMethod.POST )
	public String updateBoardPOST(RedirectAttributes rttr,
			                 /* @ModelAttribute */ BoardVO uvo) throws Exception{
		logger.debug(" updateBoardPOST() 호출 ");
		// 전달된 정보 저장(수정할 데이터)
		logger.debug("uvo : "+uvo);
		// 서비스 - 디비에 게시판 글내용 수정 동작
		service.modifyBoard(uvo);	
		// 상태정보 전달
		rttr.addFlashAttribute("result", "MODOK");
		// 페이지로 이동(리스트)
		return "redirect:/board/listALL";
	}
	
	
	// 글 정보 삭제 
	@RequestMapping(value = "/remove",method = RequestMethod.POST)
	public String removeBoardPOST(RedirectAttributes rttr
			,@RequestParam("bno") int bno) throws Exception{
		logger.debug("removeBoardPOST() 호출 ");
		// 전달정보 저장(bno)
		logger.debug("bno : "+bno);
		
		// 서비스 - 글정보 삭제 동작 호출
		service.removeBoard(bno);
		// 상태정보 전달
		rttr.addFlashAttribute("result","DELOK");
		
		// 페이지 이동(리스트)		
		return "redirect:/board/listALL";
	}
	
		// http://localhost:8088/board/listPage?page=3&pageSize=20
		// 게시판 글 목록
		@RequestMapping(value = "/listPage",method = RequestMethod.GET )
		public String listPageGET(PageVO vo,HttpSession session,Model model,@ModelAttribute("result") String result) throws Exception{
			logger.debug(" listPageGET() 호출 ");
			logger.debug(" result : "+result);
			
			// 서비스 - 페이징처리된 리스트정보 가져오기
			List<BoardVO> boardList = service.getBoardListPage(vo);
			logger.debug("boardList : "+boardList);
			
			// 페이징처리 (하단부) 정보저장객체
			PageMaker pm = new PageMaker();
			pm.setPageVO(vo);
			pm.setTotalCount(155648);
			
			
			// 조회수 체크 값
			session.setAttribute("checkViewCnt", true);
			
			// 연결된 뷰페이지로 전달 (뷰-출력)
			model.addAttribute("boardList", boardList);
			model.addAttribute("pm", pm);
			
			return "/board/listALL";
		}
		
	
	
	
}// controller
 