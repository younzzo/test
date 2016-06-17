package com.study.springrest.persistence;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.springrest.domain.ReplyVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class ReplyDAOImplTest {
	@Inject
	private ReplyDAO dao;
	
	@Test
	public void insertTest() throws Exception{
		ReplyVO vo=new ReplyVO();
		vo.setContent("&&&&&&&");
		vo.setUser_name("$$$$$$$");
		vo.setBoard_no(32);
		dao.insert(vo);
	}
	@Test
	public void getTest() throws Exception{
		ReplyVO vo=dao.get(1);
		
		//System.out.println("¿Ã∏ß¿Ã πª±Ó?: "+vo.user_name);
	}
	@Test
	public void getListTest() throws Exception{
		List<ReplyVO> list=dao.getList(40);
		Iterator<ReplyVO> it=list.iterator();
		
		while(it.hasNext()){
			ReplyVO vo=it.next();
		}
	}
	@Test
	public void updateTest() throws Exception{
		
		ReplyVO vo=new ReplyVO();
		vo.setContent("πŸ≤Ò!!!!!!!!!!!");
		vo.setUser_name("§∑§∑§∑§∑§∑§∑");
		vo.setBoard_no(41);
		dao.update(vo);
	
	}
	@Test
	public void deleteTest() throws Exception{
		dao.delete(13);
	}
}
