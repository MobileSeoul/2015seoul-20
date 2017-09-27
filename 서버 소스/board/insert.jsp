<%@page import="com.board.model.Board"%>
<%@page import="com.board.model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	BoardDAO boardDAO = new BoardDAO();
%>
<%
	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");
	String location = request.getParameter("location");
	
	Board dto = new Board();
	dto.setTitle(title);
	dto.setWriter(writer);
	dto.setContent(content);
	dto.setLocation(Integer.parseInt(location));
	
	boardDAO.insert(dto);
%>