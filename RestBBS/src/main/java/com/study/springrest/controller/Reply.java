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
@RequestMapping("/reply") // �̰Ŷ� �ؿ� �� �����ؼ�
public class Reply {

	@Inject
	private ReplyService service;

	// /����/ ���
	@RequestMapping(value = "/{board_no}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("board_no") Integer board_no) {
		ResponseEntity<List<ReplyVO>> entity = null; //ajax ���
		try{
			entity=new ResponseEntity<>(service.getList(board_no), HttpStatus.OK);
		}catch(Exception e){
			entity=new ResponseEntity<>(HttpStatus.BAD_REQUEST); //�������ٰ� ���������� �ֱ⶧���� catch������
		}
		return entity;
	}
	// /���� /����
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
	// /����/ ���� ����
		@RequestMapping(value = "/{reply_no}", method = RequestMethod.DELETE)
		public ResponseEntity<String> delete(@PathVariable("reply_no") Integer reply_no) {
			ResponseEntity<String> entity = null; //ajax ���
			try{
				service.delete(reply_no);
				entity=new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}catch(Exception e){ 
				entity=new ResponseEntity<>(HttpStatus.BAD_REQUEST); //�������ٰ� ���������� �ֱ⶧���� catch������
			}
			return entity;
		}
	
		// /����/ ����¡
		@RequestMapping(value = "/{board_no}/{page}", method = RequestMethod.GET)
		public ResponseEntity<Map<String, Object>> listPage(@PathVariable("board_no") Integer board_no, @PathVariable("page") Integer page) {
			ResponseEntity<Map<String, Object>> entity = null; //ajax ���
			try{
				//������ ó���� ���� Criteria ����
				Criteria criteria=new Criteria();
				criteria.setPage(page);
				
				//���� ���� ��������
				int replyTotal=service.getTotalCount(board_no);
				//���� ��������				
				List<ReplyVO> list=service.getListPage(board_no, criteria);
				//������ ����Ŀ�� �� ��� ������, ���� �������� �ִ� Criteria �Ѱܼ�
				//������ �� ���
				PageMaker pm=new PageMaker(criteria, replyTotal);
				
				//���������� Map�� ���� ��ϰ� ����¡ ó�� ���� ��Ƽ�
				Map<String, Object> map= new HashMap<>();
				map.put("l", list);
				map.put("p", pm);
				
				//�������� �����Ѵ�.
				entity=new ResponseEntity<>(map, HttpStatus.OK);
				
			}catch(Exception e){
				e.printStackTrace();
				entity=new ResponseEntity<>(HttpStatus.BAD_REQUEST); //�������ٰ� ���������� �ֱ⶧���� catch������
			}
			return entity;
		}

	// /����/new �۾��� ������
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView write(Locale locale, Model model) {
		return new ModelAndView("/write");
	}
}