package com.yang.pojo;

import java.util.Date;

public class Article {
	private int articleid;
	private String title;
	private String content;
	private String type;
	private String userid;
	private String username;
	private String time;
	private int clicknum=0;
	
	
	public int getArticleid() {
		return articleid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getClicknum() {
		return clicknum;
	}	
}
