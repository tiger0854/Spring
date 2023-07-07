package com.itwillbs.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

@RestController
@RequestMapping("/boards")
public class BoardRestController {

	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);
	
	@Autowired
	private BoardService service;
	
	// 주소: /boards + JSON데이터 (POST)
	@RequestMapping(value = "",method = RequestMethod.POST)
	public ResponseEntity<String> addBoard(@RequestBody BoardVO vo) throws Exception{
		logger.debug(" addBoard() 호출 !!! ");
		logger.debug(" vo "+vo);
		
		service.insertBoard(vo);

		return new ResponseEntity<String>("addOK",HttpStatus.OK);
	}
	
	// 주소: /boards   (GET)
	@RequestMapping(value = "",method = RequestMethod.GET)
	public ResponseEntity<List<BoardVO>> listBoard() throws Exception{
		logger.debug(" listBoard() 호출 ");
		logger.debug(" 주소 : /boards (get방식) ");
		List<BoardVO> boardList = service.getListAll();
		logger.debug("boardList.size:"+boardList.size());
		
		return new ResponseEntity<List<BoardVO>>(boardList,HttpStatus.OK);
	}
	
	// 주소 : /boards/100  (GET)
	@RequestMapping(value = "/{bno}",method=RequestMethod.GET)
	public ResponseEntity<BoardVO> readBoard(@PathVariable("bno") Integer bno) throws Exception{
		
		logger.debug(" readBoard() 호출 ");
		logger.debug(" bno :  "+bno);
		
		BoardVO vo = service.getBoard(bno);
		
		logger.debug("vo:"+vo);
		
		ResponseEntity<BoardVO> respEntity = null;
		
//		try {
//			// 게시판 글 있음
//			respEntity 
//			  = new ResponseEntity<BoardVO>(vo,HttpStatus.OK);
//		}catch (Exception e) {
//			// 게시판 글 없음
//			respEntity
//			  = new ResponseEntity<BoardVO>(vo,HttpStatus.INTERNAL_SERVER_ERROR);
//		}
		
		if(vo == null) {
			// 게시판 글 없음
			respEntity
			  = new ResponseEntity<BoardVO>(vo,HttpStatus.INTERNAL_SERVER_ERROR);
			//return new ResponseEntity<BoardVO>(vo,HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			// 게시판 글 있음
			respEntity 
			  = new ResponseEntity<BoardVO>(vo,HttpStatus.OK);
			//return new ResponseEntity<BoardVO>(vo,HttpStatus.OK);
		}
		
		return respEntity;
	}
	
	
	// 주소 : /boards/1  + JSON   (PUT)
	@RequestMapping(value = "/{bno}",method = RequestMethod.PUT)
	public ResponseEntity<String> modifyBoard(@PathVariable("bno") Integer bno,
											  @RequestBody BoardVO vo) throws Exception{
		logger.debug("modifyBoard() 호출");
		
		logger.debug("vo :"+vo);
		vo.setBno(bno);
		logger.debug("vo :"+vo);
		
		service.modifyBoard(vo);
		
		return new ResponseEntity<String>("mod_OK",HttpStatus.OK);
	}
	
	
	// 주소 : /boards/100		DELETE
	@RequestMapping(value = "/{num}",method = RequestMethod.DELETE)
	public ResponseEntity<String> removeBoard(@PathVariable("num") Integer bno) throws Exception{
		
		logger.debug(" removeBoard() 호출 ");
		logger.debug(" bno : "+bno);
		
		service.removeBoard(bno);
		
		return new ResponseEntity<String>("del_OK",HttpStatus.OK);
	}
	
	
	
	
	
	
	

}
