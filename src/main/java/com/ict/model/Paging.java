package com.ict.model;

public class Paging {
	// 페이징 기법 : 핵심은 전체 정보에서 원하는 일부분만 가져오기
	//				 그 일부분이란 해당 페이지에 시작번호와 끝번호, 시작블록과 끝블록을 말한다.
	// 즉, 실제로 구하고자 하는 것은 시작번호, 끝번호, 시작블록, 끝블록이다.
	// 오라클에서 select 사용시 가장 왼쪽에 자동으로 생기는 롤넘버를 이용한다. (desc로 최근 것이 1번으로 가게 정렬)
	// 1. select ROWNUM r_num, a.* from (select * from bbs_t order by b_idx desc)a; 로 rownum을 테이블로 가져온다.
	// 2. 이 rownum을 이용해서 페이지의 조건을 만든다. (페이징에 사용되는 쿼리)
	/*
	  select * from 
	  	(select ROWNUM r_num, a.* from 
	  		(select * from bbs_t order by b_idx desc) a) 
	  where r_num between A(시작번호) and B(끝번호)
	 */ 
	
	private int nowPage = 1; 		// 현재 페이지
	private int nowBlock = 1;		// 현재 블록
	private int totalRecord = 0;	// 전체 게시물의 수
	private int totalPage = 0;		// 전체 페이지의 수
	private int totalBlock = 0;		// 전체 블록의 수
	private int numPerPage = 5;		// 한 페이지에 존재하는 줄의 수(원글의 수)
	private int pagePerBlock = 3;	// 한 블록안에 존재하는 페이지의 수 (1 ~ n 페이지까지) => 즉, 한 페이지에 보여주는 블록의 수
	
	private int begin = 0;			// 시작페이지
	private int end = 0;			// 끝페이지
	private int beginBlock = 0;		// 시작블록
	private int endBlock = 0;		// 끝블록
	
	// getter/setter
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getNowBlock() {
		return nowBlock;
	}
	public void setNowBlock(int nowBlock) {
		this.nowBlock = nowBlock;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalBlock() {
		return totalBlock;
	}
	public void setTotalBlock(int totalBlock) {
		this.totalBlock = totalBlock;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	public int getPagePerBlock() {
		return pagePerBlock;
	}
	public void setPagePerBlock(int pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getBeginBlock() {
		return beginBlock;
	}
	public void setBeginBlock(int beginBlock) {
		this.beginBlock = beginBlock;
	}
	public int getEndBlock() {
		return endBlock;
	}
	public void setEndBlock(int endBlock) {
		this.endBlock = endBlock;
	}
}
