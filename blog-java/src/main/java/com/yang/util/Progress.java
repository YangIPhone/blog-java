package com.yang.util;

/**
 * 存储文件进度的实体类
 * @author Shinelon
 * 
 */
public class Progress {
	private String lsize;
	private String progress;
	private String speed;
	private String ltime;

	public String getLsize() {
		return lsize;
	}
	public void setLsize(String lsize) {
		this.lsize = lsize;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getLtime() {
		return ltime;
	}
	public void setLtime(String ltime) {
		this.ltime = ltime;
	}
	
}
