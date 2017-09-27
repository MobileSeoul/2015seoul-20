<%@page import="com.binmap.model.BinmapDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	BinmapDAO binmapDAO = new BinmapDAO();
%>
<%
	String bin_id = request.getParameter("bin_id");
	String status = request.getParameter("status");
	binmapDAO.update(bin_id, status);
%>
