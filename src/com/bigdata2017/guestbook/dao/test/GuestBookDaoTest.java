package com.bigdata2017.guestbook.dao.test;

import java.util.List;

import com.bigdata2017.guestbook.dao.GuestBookDao;
import com.bigdata2017.guestbook.vo.GuestBookVO;

public class GuestBookDaoTest {

	public static void main(String[] args) {
		delete();
		insert();
		getList();
	}
	
	public static void delete() {
		new GuestBookDao().delete(2L, "1234");
		
	}
	
	
	
	public static void insert() {
		GuestBookVO vo = new GuestBookVO();
		
		vo.setName("gd");
		vo.setPassword("1234");
		vo.setContent("ddddddddd");
		new GuestBookDao().insert(vo);
	}
	
	
	
	
	public static void getList() {
		GuestBookDao dao = new GuestBookDao();
		List<GuestBookVO> list = dao.getList();
		
		for (GuestBookVO vo : list) {
			System.out.println( vo );
		}
		
	}
		

}
