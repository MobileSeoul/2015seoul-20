/*
 	����¡ ó���� ���뼺�� ����, ������ Ŭ������ �����ص�
 */

package com.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class paging {
	private int currentPage =1;
	private int totalRecord;
	private int pageSize =15;
	private int totalPage;
	private int blockSize =10;
	private int firstPage;
	private int lastPage;
	private int curPos;
	private int num;
	
	public void init(HttpServletRequest request, List list){
		//����ڰ� ������ ��ȣ�� �����ʰ� ������ 1�������� �����ϰ�,
		// �� �̿��� ��쿣 �Ѱܹ��� currentPage �Ķ���� ������ �������� ��ü
		if(request.getParameter("currentPage")!=null)
		{
			currentPage =Integer.parseInt(request.getParameter("currentPage"));
		}
		totalRecord = list.size();
		totalPage  = (int)Math.ceil((double)totalRecord/pageSize);
		firstPage = currentPage-((currentPage-1)%blockSize);
		lastPage = firstPage+(blockSize-1);
		curPos = (currentPage-1)*pageSize;
		num = totalRecord-curPos;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getCurPos() {
		return curPos;
	}

	public void setCurPos(int curPos) {
		this.curPos = curPos;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	
}
