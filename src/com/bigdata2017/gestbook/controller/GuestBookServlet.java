package com.bigdata2017.gestbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bigdata2017.guestbook.dao.GuestBookDao;
import com.bigdata2017.guestbook.vo.GuestBookVO;

@WebServlet("/gb")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//jsp 에서  보낸 a(목적페이지)를 받는다.
		String actionName = request.getParameter( "a" );
		
		
		//받은 a의 값에 따라 알맞은 기능을 실행한다.
		if ( "list".equals( actionName )) {
			
		} else if ( "deleteform".equals( actionName )) {
			// jsp에서 deleteform을 요청하면 저장된 jsp 페이지로 request를 연장해준다.
			RequestDispatcher rd = request.getRequestDispatcher( "/WEB-INF/views/deleteform.jsp" );
			rd.forward( request, response);
			
			
		} else if ( "insert".equals( actionName )) {
			// jsp에서 insert를 요청하면 받은 인자들을 저장해주고, redirect로 첫 화면을 요청해준다.
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestBookVO vo = new GuestBookVO();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContent(content);
			
			new GuestBookDao().insert( vo );
			
			response.sendRedirect( request.getContextPath()+"/gb" );
			
		} else if ( "delete".equals( actionName )) {
			// jsp에서 delete를 요청하면 삭제하고 redirect로 첫 화면을 요청해 준다.
			Long   no 		= Long.parseLong( request.getParameter("no") );
			String password = request.getParameter("password");
			
			new GuestBookDao().delete( no, password );
						
			response.sendRedirect( request.getContextPath()+"/gb" );
			
		} else  {
			// 정의되지않은 요청을 받을 경우 default로 list 화면(index)를 보여주도록 한다.
			/* default 요청 처리 (list) */
			GuestBookDao dao = new GuestBookDao();
			List<GuestBookVO> list = dao.getList();
			
			request.setAttribute("list", list);
			
			//Request 연장 - WEB-INF 는 외부에서는 접근이 안되지만 내부(서블렛)에서는 접근이 가능하다 
			RequestDispatcher rd = request.getRequestDispatcher( "/WEB-INF/views/index.jsp" );
			rd.forward( request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
