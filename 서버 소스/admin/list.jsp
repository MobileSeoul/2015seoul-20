
<%@page import="com.board.model.Board"%>
<%@page import="com.board.model.BoardDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.common.Paging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%!
	Paging paging = new Paging();
	BoardDAO boardDAO = new BoardDAO();
%>
<%
	
	List<Board> list = boardDAO.selectAll();
	paging.init(request, list);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
#box {
	border: 1px solid #CCCCCC
}

#title {
	font-size: 9pt;
	font-weight: bold;
	color: #7F7F7F;
	돋움
}

#category {
	font-size: 9pt;
	color: #7F7F7F;
	돋움
}

#keyword {
	width: 80px;
	height: 17px;
	font-size: 9pt;
	border-left: 1px solid #333333;
	border-top: 1px solid #333333;
	border-right: 1px solid #333333;
	border-bottom: 1px solid #333333;
	color: #7F7F7F;
	돋움
}

#paging {
	font-size: 9pt;
	color: #7F7F7F;
	돋움
}

#list td {
	font-size: 9pt;
}

#copyright {
	font-size: 9pt;
}

a {
	text-decoration: none
}

img {
	border: 0px
}
</style>
</head>
<body>
	<table id="box" align="center" width="800" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="5"><img src="images/ceil.gif" width="800"
				height="25"></td>
		</tr>
		<tr>
			<td height="2" colspan="5" bgcolor="#6395FA"><img
				src="images/line_01.gif"></td>
		</tr>
		<tr id="title" align="center">
			<td width="50" height="20">번호</td>
			<td width="303" height="20">제목</td>
			<td width="100" height="20">글쓴이</td>
			<td width="100" height="20">날짜</td>
			<td width="50" height="20">상태</td>
		</tr>
		<tr>
			<td height="1" colspan="5" bgcolor="#CCCCCC"></td>
		</tr>
		
		<tr>
			<td colspan="5" id="list">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<%
		    			int curPos=paging.getCurPos(); // 페이지당 시작 List Index
		    			int num = paging.getNum(); //게시물 번호
		    		%>
					<%for(int i=1;i<paging.getPageSize();i++) { %>
		    		<%if(num<1)break; %>
		    		<%Board dto = list.get(curPos++); %>
					<tr align="center" height="20px"
						onMouseOver="this.style.background='#FFFF99'"
						onMouseOut="this.style.background=''">
						<td width="50"><%=num-- %></td>
						<td width="303"><a href="/admin/detail.jsp?board_id=<%=dto.getBoard_id()%>"><%=dto.getTitle() %></a></td>
						<td width="100"><%=dto.getWriter() %></td>
						<td width="100"><%=dto.getRegdate().substring(0, 10) %></td>
						<td width="50">
						<%if(dto.getStatus() == 0) {%>
							확인안함
						<%}else if(dto.getStatus() == 1) { %>
							확인
						<%} %>
						</td>
					</tr>
					<tr>
						<td height="1" colspan="5" background="images/line_dot.gif"></td>
					</tr>
					<%} %>
				</table>
			</td>
		</tr>
		<tr>
			<td id="paging" height="20" colspan="5" align="center">
				<%if(paging.getFirstPage()-1==0){ %> <a href="javascript:alert('이전 페이지가 없습니다.')">◀</a>
				<%}else{ %> <a href="/admin/list.jsp?currentPage=<%=paging.getFirstPage()-1%>">◀</a>
				<%} %> <%
				for (int i = paging.getFirstPage(); i <= paging.getLastPage(); i++) {
				if(i>paging.getTotalPage())break;%> <a <%if(paging.getCurrentPage()==i){ %>
				class="pageStyle" <%} %> href="/admin/list.jsp?currentPage=<%=i%>">[<%=i%>]
			</a> <%
 				}
 			%> <%if(paging.getLastPage()+1>paging.getTotalPage()){ %> <a
				href="javascript:alert('다음 페이지가 없습니다.')"> ▶</a> <%}else{ %> <a
				href="/admin/list.jsp?currentPage=<%=paging.getLastPage()+1%>"> ▶</a> <%} %>

			</td>
		</tr>
		
		
		<tr>
			<td height="1" colspan="5" bgcolor="#CCCCCC"></td>
		</tr>
		<tr>
			<td height="20" colspan="5" align="center" id="copyright">Copyright
				Nabingation All Rights Reserved</td>
		</tr>
	</table>
</body>
</html>
