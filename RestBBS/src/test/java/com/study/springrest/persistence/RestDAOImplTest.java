package com.study.springrest.persistence;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.study.springrest.domain.RestVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class RestDAOImplTest {
	@Inject
	private RestDAO dao;
	
	@Test
	public void insertTest() throws Exception{
		RestVO vo=new RestVO();
		
		vo.setTitle("ggggg");
		vo.setContent("dddd");
		vo.setUser_name("sssssss");
		
		dao.insert(vo);
	}
	@Test
	public void getTest() throws Exception{
		RestVO vo=dao.get(1);
		
		//System.out.println("�̸��� ����?: "+vo.user_name);
	}
	@Test
	public void getListTest() throws Exception{
		List<RestVO> list=dao.getList();
		Iterator<RestVO> it=list.iterator();
		
		while(it.hasNext()){
			RestVO vo=it.next();
			System.out.println("�̸�: "+vo.getUser_name());
		}
	}
	@Test
	public void updateTest() throws Exception{
		
		RestVO vo=new RestVO();
		
		vo.setBoard_no(3);
		vo.setTitle("===���� �ٲ�2222222====");
		vo.setContent("***����ٲ�22222222***");
		vo.setUser_name("^^^�۾��� �ٲ�22222222^^^");
		vo.setView_cnt(999);
		
		dao.update(vo);
	
	}
	@Test
	public void deleteTest() throws Exception{
		dao.delete(7);
	}
}
