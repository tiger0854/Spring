package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

// @Service : 스프링(root-context.xml)에서 해당 객체를 서비스 객체로
//            인식하도록 설정

@Service
public class MemberServiceImpl implements MemberService{

	// DAO-Controller 연결
	
	// DAO 객체 접근필요함 => 의존관계
	@Inject
	private MemberDAO mdao;
	
	
	@Override
	public void memberJoin(MemberVO vo) {
		mdao.insertMember(vo);
	}

	@Override
	public MemberVO memberLogin(MemberVO vo) {
		MemberVO resultVO = mdao.loginMember(vo);
		return resultVO;
	}

	
	@Override
	public MemberVO getMember(String id) {
		return mdao.getMember(id);
	}

	@Override
	public Integer memberModify(MemberVO uvo) {
		return mdao.updateMember(uvo);
	}

	@Override
	public Integer memberRemove(MemberVO dvo) {
		return mdao.deleteMember(dvo);
	}

	@Override
	public List<MemberVO> getMemberList() {
		return mdao.getMemberList();
	}
	
	
	
	
	
}
