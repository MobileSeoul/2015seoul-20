<%@page import="com.binmap.model.BinmapDAO"%>
<%@page import="com.binmap.model.Binmap"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%!BinmapDAO dao = new BinmapDAO();%>
<%
	String lati=request.getParameter("lati");
	String longi=request.getParameter("longi");
	
	List<Binmap> list = dao.selectAll(lati, longi);

	StringBuilder sb = new StringBuilder();
	sb.append("{\"trashbin\":[");
	for (int i=0; i<list.size(); i++) {
		Binmap dto = list.get(i);

		sb.append("{");
		sb.append("\"bin_id\":\"" + dto.getBin_id() + "\"");
		sb.append(", \"gu\":\"" + dto.getGu() + "\"");
		sb.append(", \"latitude\":\"" + dto.getLatitude() + "\"");
		sb.append(", \"longitude\":\"" + dto.getLongitude() + "\"");
		sb.append(", \"status\":\"" + dto.getStatus() + "\"");
		sb.append(", \"recycle\":\"" + dto.getRecycle() + "\"");
		sb.append("}");
		
		if(i!=list.size()-1){
			sb.append(",");
		}
	}
	sb.append("]}\n");
	
	out.print(sb.toString());
	
%>