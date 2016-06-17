package com.study.springrest.service;

import java.util.List;

import com.study.springrest.domain.Criteria;
import com.study.springrest.domain.ReplyVO;

public interface ReplyService {
	public ReplyVO get(int reply_no);
	public List<ReplyVO> getList(int board_no);
	public void update(ReplyVO vo);
	public void insert(ReplyVO vo);
	public void delete(int reply_no);
	public List<ReplyVO> getListPage(int board_no, Criteria cri); //����¡
	public int getTotalCount(int board_no);
	
}
