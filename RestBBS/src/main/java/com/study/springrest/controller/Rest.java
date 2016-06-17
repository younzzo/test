package com.study.springrest.controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.study.springrest.domain.RestVO;
import com.study.springrest.service.RestService;

@RestController
@RequestMapping("/rest") // �̰Ŷ� �ؿ� �� �����ؼ�
public class Rest {

	@Inject
	private RestService service;

	// /����/ ���
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(Locale locale, Model model) {
		//ModelAndView listPage = new ModelAndView();
		//listPage.setViewName("list"); //jsp ���� �̸�
		List<RestVO> List = service.getList();
		//listPage.addObject("list", List);
		//return listPage;
		return new ModelAndView("list").addObject("list", List);
	}

	// /����/�۹�ȣ ��
	@RequestMapping(value = "/{board_no}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable("board_no") Integer board_no) {
		ModelAndView bbb = new ModelAndView();
		bbb.setViewName("detail");
		RestVO vo = service.get(board_no);
		bbb.addObject("vo", vo);
		return bbb;
	}
	
	// /����/new �۾��� ������
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView write(Locale locale, Model model) {
		return new ModelAndView("write");
	}

	// /���� / method=post
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView insert(RestVO vo, Model model) {
		ModelAndView insert = new ModelAndView();
		insert.setViewName("list");
		service.insert(vo);
		insert.addObject("list", vo);
		return new ModelAndView("redirect:/rest");
	}
	
	// /���� / ����
	@RequestMapping(value = "/{board_no}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable("board_no") Integer board_no) {
		service.delete(board_no);
		return new ModelAndView("redirect:/rest");
	}
}