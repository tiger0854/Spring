package com.itwillbs.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;

// - 컨트롤러별 공통 주소(URI) 설계 
// - 각 기능별 주소(URI) 설계 
// - 각 URI별 호출 방식 설정(GET/POST)
//   => 사용자의 정보입력,조회 (GET)
//   => 데이터 처리, DB접근 (POST)
// - 결과처리, 페이지 이동 설계
// - 예외처리 



@Controller
@RequestMapping(value = "/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	// 서비스정보 필요함 => 의존관계
	@Inject
	private MemberService mService;
	
	// 동작 구현 -> 메서드 설계
	
	//http://localhost:8088/member/MemberJoin.me(x)
	//http://localhost:8088/member/join
	// 회원가입 처리 - 정보입력
	@RequestMapping(value = "/join",method = RequestMethod.GET)
	public String memberJoinGET() {
		logger.debug(" memberJoinGET() 호출! ");
		
		logger.debug(" /member/MemberJoin.jsp 뷰페이지 연결(자동)");
		
		return "/member/MemberJoin";
	}
	// 회원가입 처리 - 정보처리
	@RequestMapping(value = "/join",method = RequestMethod.POST)
	public String memberJoinPOST(HttpServletRequest request,
			             MemberVO vo) throws Exception {
					// 매개변수선언 (vo) 관련정보(파라메터)자동 수집  
		logger.debug(" memberJoinPOST() 호출! ");
		
		// 한글처리 -> 필터 
		//request.setCharacterEncoding("UTF-8");
		// 전달정보 저장(회원가입정보-파라메터)
		//		MemberVO vo = new MemberVO();
		//		vo.setUserid(request.getParameter("userid"));
		
		logger.debug(vo+"");
		
		// 서비스-회원가입 메서드
		//   => DAO - 회원가입메서드 호출
		mService.memberJoin(vo);
		
		// 페이지 이동 -> 로그인 페이지
		return "redirect:/member/login";
	}
	
	// 로그인 - 정보 입력(get)
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public void loginGET() {
		logger.debug(" loginGET() 호출 ");
		logger.debug(" 연결된 뷰페이지로 이동 (/member/login.jsp)");
	}
	
	
	// 로그인 - 정보 처리(post)
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String loginPOST(MemberVO vo,HttpSession session/* ,@ModelAttribute("userid") String userid */) {
		// 전달정보 (로그인 ID,PW) 저장
		logger.debug(vo+"");
		
		// 서비스 -> DAO - 로그인체크 
		MemberVO resultVO = mService.memberLogin(vo);
		
		logger.debug(resultVO+"");
		// 로그인 여부에 따라서 페이지 이동
		if(resultVO != null) {
			// 	O
			//   메인페이지로 이동(redirect)
			//   로그인 아이디를 세션에 저장
			session.setAttribute("id", resultVO.getUserid());
			logger.debug(" 로그인 성공! ");
			return "redirect:/member/main";
		}else {
			// 	X
			//   다시 로그인페이지로 이동
			logger.debug(" 로그인 실패! ");
			return "redirect:/member/login";
		}
	}
	
	// 메인페이지 
	@RequestMapping(value = "/main",method = RequestMethod.GET)
	public void mainGET() {
		logger.debug(" mainGET() 호출 ");
		
		logger.debug(" /member/main.jsp페이지 이동 ");
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logoutGET(HttpSession session) {
		logger.debug("logoutGET() 호출!");
		
		// 세션정보 초기화
		session.invalidate();
		
		//return "redirect:/member/login";
		return "redirect:/member/main";
	}
	
	// 회원정보 조회
	@RequestMapping(value = "/info",method = RequestMethod.GET)
	public void infoGET(HttpSession session,Model model) {
		logger.debug(" infoGET() 호출 ");
		// 회원정보 가져오기 - 아이디정보(세션) 디비에 있는 회원정보 모두조회
		// 서비스 -> DAO
		String id = (String)session.getAttribute("id");
		MemberVO resultVO = mService.getMember(id);
		
		logger.debug("@@@@@@@ resultVO : "+resultVO);
		// 연결된 뷰페이지에 전달 => Model 객체
		model.addAttribute("resultVO", resultVO);
		//model.addAttribute(resultVO);
		
		model.addAttribute(mService.getMember(id));
		
		// 페이지 이동
		logger.debug(" /member/info.jsp 페이지로 이동 ");
	}
	
	// 회원정보 수정 - 기존의 정보 출력 + 수정정보 입력
	@RequestMapping(value = "/modify",method = RequestMethod.GET)
	public void modifyGET(@SessionAttribute String id,Model model) {
		logger.debug(" modifyGET() 호출 ");
		
		logger.debug("id : "+id);
		
		// 기존의 회원정보를 화면에 보여주기 
		// (이름,이메일 수정하기 - ID/PW동일한경우)
		MemberVO resultVO = mService.getMember(id);
		
		// Model 객체 사용 => 정보 저장 (전달 준비)
		model.addAttribute("resultVO", resultVO);
		
		// /member/modify.jsp 페이지 이동(출력)
	}
	
	// 회원정보 수정 - 정보 수정(처리)
	@RequestMapping(value = "/modify",method = RequestMethod.POST)
	public String modifyPOST(MemberVO uvo) {
		logger.debug(" modifyPOST() 호출 ! ");
		// 한글처리 = > 인코딩 필터 처리
		// 전달정보 저장(수정데이터)
		logger.debug(" 파라메터 자동수집 !! ");
		logger.debug("uvo "+uvo);
		
		// 서비스 -> 회원정보 수정가능한 기능
		int result = mService.memberModify(uvo);
		
		logger.debug("result : 	"+result);
		// 페이지 이동(main)
		
		return "redirect:/member/main";
	}
	
	// 회원삭제(탈퇴) - 탈퇴정보 입력
	@RequestMapping(value = "/remove",method = RequestMethod.GET)
	public void removeGET() {
		logger.debug(" removeGET() 호출 ");
		logger.debug(" /member/remove.jsp 페이지 이동 ");		
	}
	
	// 회원삭제(탈퇴) - 탈퇴 처리	
	@RequestMapping(value = "/remove",method = RequestMethod.POST)
	public String removePOST(@SessionAttribute String id,
			              @ModelAttribute("userpw") String userpw,
			              HttpSession session) {
		logger.debug(" removePOST() 호출 ");
		
		// (세션) 아이디 정보
		logger.debug("id : "+id);
		// 전달받은 정보 (userpw)
		logger.debug("pw : "+userpw);
		
		MemberVO vo = new MemberVO();
		vo.setUserid(id);
		vo.setUserpw(userpw);
		
		// 서비스 ->  정보 삭제처리
		int result = mService.memberRemove(vo);
		
		// 삭제 성공시 (기존 세션정보 초기화)
		if(result == 1) {
			session.invalidate();
		}
		
		return "redirect:/member/main";
	}
	
	
	// 회원정보 목록(admin)
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public void listGET(Model model) {
		logger.debug(" listGET() 호출 ");
		
		// 서비스 -> 회원목록 가져오기
		List<MemberVO> memberList = mService.getMemberList();
		// Model 객체에 저장
		model.addAttribute("memberList", memberList);
		
		// /member/list.jsp 뷰페이지 이동(출력)
	}
	
	
	

}//controller
