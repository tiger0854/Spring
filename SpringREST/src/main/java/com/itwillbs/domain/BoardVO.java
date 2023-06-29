package com.itwillbs.domain;

import java.sql.Timestamp;

public class BoardVO {
	
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private int viewcnt;
	private Timestamp regdate;
	public Integer getBno() {
		return bno;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getWriter() {
		return writer;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer + ", viewcnt="
				+ viewcnt + ", regdate=" + regdate + "]";
	}
	
	
	

}
