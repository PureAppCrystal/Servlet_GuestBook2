<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String no = request.getParameter("no");
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	hello guest book - delete form
	
	<h1>방명록 삭제하기</h1>
	<p>
		방명록을 삭제하려면,<br>
		비밀번호를 입력해 주세요.
	</p>
	
	<!-- 서브밋을 누르면 gb(서브렛)에게 히든으로 a(delete), no 와 패스워드를 전달한다  -->
	<form action= "<%=request.getContextPath() %>/gb" method="post">
		<input type="hidden" name = "a" value = "delete"/>
		<input type="hidden" name= "no" value=<%=no %> />
		비밀번호 : <input type="text" name="password" value="" />
		 <input type="submit" value="삭제"/> 
	</form> 
</body>
</html>