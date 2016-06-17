package com.study.springrest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.study.springrest.domain.Criteria;
import com.study.springrest.domain.PageMaker;
import com.study.springrest.domain.ReplyVO;
import com.study.springrest.service.ReplyService;

@RestController
@RequestMapping("/reply") // 이거랑 밑에 꺼 조합해서
public class Reply {

	@Inject
	private ReplyService service;

	// /유닛/ 목록
	@RequestMapping(value = "/{board_no}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("board_no") Integer board_no) {
		ResponseEntity<List<ReplyVO>> entity = null; //ajax 통신
		try{
			entity=new ResponseEntity<>(service.getList(board_no), HttpStatus.OK);
		}catch(Exception e){
			entity=new ResponseEntity<>(HttpStatus.BAD_REQUEST); //가져오다가 에러날수도 있기때문에 catch문으로
		}
		return entity;
	}
	// /유닛 /삽입
		@RequestMapping(value = "/{board_no}", method = RequestMethod.POST)
		public ResponseEntity<String> insert(@RequestBody ReplyVO vo, @PathVariable("board_no") Integer board_no) {
		
			ResponseEntity<String> entity = null;
			try{
				vo.setBoard_no(board_no);
				service.insert(vo);
				entity=new ResponseEntity<>("SUCCESS", HttpStatus.OK);
			}catch(Exception e){
				entity=new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
				e.printStackTrace();
			}
			return entity;
		}
	// /유닛/ 덧글 삭제
		@RequestMapping(value = "/{reply_no}", method = RequestMethod.DELETE)
		public ResponseEntity<String> delete(@PathVariable("reply_no") Integer reply_no) {
			ResponseEntity<String> entity = null; //ajax 통신
			try{
				service.delete(reply_no);
				entity=new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}catch(Exception e){ 
				entity=new ResponseEntity<>(HttpStatus.BAD_REQUEST); //가져오다가 에러날수도 있기때문에 catch문으로
			}
			return entity;
		}
	
		// /유닛/ 페이징
		@RequestMapping(value = "/{board_no}/{page}", method = RequestMethod.GET)
		public ResponseEntity<Map<String, Object>> listPage(@PathVariable("board_no") Integer board_no, @PathVariable("page") Integer page) {
			ResponseEntity<Map<String, Object>> entity = null; //ajax 통신
			try{
				//페이지 처리를 위한 Criteria 생성
				Criteria criteria=new Criteria();
				criteria.setPage(page);
				
				//덧글 개수 가져오기
				int replyTotal=service.getTotalCount(board_no);
				//덧글 가져오기				
				List<ReplyVO> list=service.getListPage(board_no, criteria);
				//페이지 메이커에 총 댓글 개수와, 현재 페이지가 있는 Criteria 넘겨서
				//페이지 값 계산
				PageMaker pm=new PageMaker(criteria, replyTotal);
				
				//최종적으로 Map에 덧글 목록과 페이징 처리 값을 담아서
				Map<String, Object> map= new HashMap<>();
				map.put("l", list);
				map.put("p", pm);
				
				//브라우저로 전송한다.
				entity=new ResponseEntity<>(map, HttpStatus.OK);
				
			}catch(Exception e){
				e.printStackTrace();
				entity=new ResponseEntity<>(HttpStatus.BAD_REQUEST); //가져오다가 에러날수도 있기때문에 catch문으로
			}
			return entity;
		}

	// /유닛/new 글쓰기 페이지
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView write(Locale locale, Model model) {
		return new ModelAndView("/write");
	}
}