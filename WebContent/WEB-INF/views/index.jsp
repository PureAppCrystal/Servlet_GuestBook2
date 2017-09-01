<%@page import="com.bigdata2017.guestbook.vo.GuestBookVO"%>
<%@page import="java.util.List"%>
<%@page import="com.bigdata2017.guestbook.dao.GuestBookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<GuestBookVO> list = (List<GuestBookVO>)request.getAttribute( "list" );
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	hello guest book - insert&list
	
	<h1>방명록</h1>
	<p>
		방명록을 남기려면,<br>
		아래 항목을 기입하고 확인을 눌러주세요.
	</p>
	<form action="/guestbook1/insert.jsp" method="post">
	    <table border="1px" cellspacing=0 cellpadding="2px">
			<tr>
				<td>이름</td>
				<td> <input type="text" name="name" value="" />   </td>
				<td>비밀번호</td>
				<td> <input type="password" name="password" value=""/>   </td>
			</tr>
			<tr>
				<td colspan="4">  <textarea rows="15" cols="70" name="content"></textarea>  </td>
			</tr>
			<tr>
				<td colspan="4" align="right"> <input type="submit" value="확인"/>   </td>
			</tr>
		</table>
	</form>
	<br>
	<br>
	
	<%
		int count = list.size();
		for (GuestBookVO vo : list) {
	%>
	<table border="1px" cellspacing=0 cellpadding="2px">
		<tr>
			<td> <%= count-- %>    </td>
			<td> <%= vo.getName() %>  </td>
			<td> <%= vo.getReg_date() %>  </td>
			<!-- gb 서브렛에게 겟방식으로 a(삭제페이지 요청)와 넘버의 값을 전달해준다 -->
			<td> <a href="<%= request.getContextPath() %>/gb?a=deleteform&no=<%= vo.getNo()%>">삭제</a>   </td>
		</tr>
		<tr>
			<td colspan="4">  <textarea rows="1" cols="70" name="content"> <%= vo.getContent() %></textarea>  </td>
		</tr>
	</table>
	<br>
	<%
		}
	%>
	
	
	
	
	
</body>
</html>