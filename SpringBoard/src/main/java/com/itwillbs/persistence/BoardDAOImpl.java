package com.itwillbs.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	// 디비연결정보,mapper접근 => SqlSession 객체
	@Autowired
	private SqlSession sqlSession;
	
	private static final Logger logger 
	     = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	// mapper의 namespace 정보
	private static final String NAMESPACE
				="com.itwillbs.mapper.BoardMapper";

	@Override
	public void createBoard(BoardVO vo) throws Exception {
		logger.debug(" sqlSession 객체-Mybatis-mapper 접근-DB접근 ");
		int result = sqlSession.insert(NAMESPACE + ".create", vo);
		
		if(result != 0)
			logger.debug("글쓰기 완료!");
	}

	@Override
	public List<BoardVO> readBoardListAll() throws Exception {
		logger.debug(" readBoardListAll() 호출 ");
		
		return sqlSession.selectList(NAMESPACE +".listAll");
	}

	@Override
	public void updateViewcnt(Integer bno) throws Exception {
		logger.debug(" updateViewcnt(Integer bno) 호출 ");
		sqlSession.update(NAMESPACE + ".upViewcnt", bno);
	}

	
	@Override
	public BoardVO readBoard(Integer bno) throws Exception {
		logger.debug(" readBoard(Integer bno) 호출 ");
		
		// 정보(+수정) 전달
		//	BoardVO vo = sqlSession.selectOne(NAMESPACE + ".getBoard", bno);
		//  vo.setXXXX(수정가능);
		//	return vo;
		
		// 정보 전달
		return sqlSession.selectOne(NAMESPACE + ".getBoard", bno);
	}
	
	
}
