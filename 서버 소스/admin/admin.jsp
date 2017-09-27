<%@page import="com.binmap.model.Binmap"%>
<%@page import="com.binmap.model.BinmapDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%!BinmapDAO binmapDao = new BinmapDAO();%>
<%
	List<Binmap> list = binmapDao.selectAll();
	//out.print(list.size());

	StringBuffer sb = new StringBuffer();
	sb.append("[");
	for (int i = 0; i < list.size(); i++) {
		Binmap dto = list.get(i);
		sb.append("[" + "\"" + dto.getBin_id() + "\", " + dto.getUseRate() + "]");
		if (i != list.size() - 1) {
			sb.append(",");
		}
	}
	//  ['Lagos', 16.1]

	sb.append("]");
	out.print(sb);
%>