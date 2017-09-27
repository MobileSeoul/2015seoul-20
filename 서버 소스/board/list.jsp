<%@page import="java.util.List"%>
<%@page import="com.board.model.BoardDAO"%>
<%@page import="com.board.model.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	BoardDAO dao = new BoardDAO();
	List<Board> list = dao.selectAll();
	System.out.println(list.size());
	StringBuilder sb = new StringBuilder();
	sb.append("{\"board\":[");
	for (int i = 0; i < list.size(); i++) {
		Board dto = list.get(i);

		sb.append("{");
		sb.append("\"board_id\":" + dto.getBoard_id() + "");
		sb.append(", \"writer\":\"" + dto.getWriter() + "\"");
		sb.append(", \"title\":\"" + dto.getTitle() + "\"");
		sb.append(", \"content\":\"" + dto.getContent() + "\"");
		sb.append(", \"regdate\":\"" + dto.getRegdate() + "\"");
		sb.append(", \"status\":" + dto.getStatus() + "");
		sb.append(", \"location\":" + dto.getLocation() + "");
		sb.append("}");
		
		if(i!=list.size()-1){
			sb.append(",");
		}
	}
	sb.append("]}\n");
	
	out.print(sb.toString());
%>
