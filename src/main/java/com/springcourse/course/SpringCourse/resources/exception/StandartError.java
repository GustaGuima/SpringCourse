package com.springcourse.course.SpringCourse.resources.exception;

import java.io.Serializable;

public class StandartError implements Serializable{
		private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String message;
	private long timeStamp;
	
	
	
	//Constructers
		public StandartError(Integer status, String message, long timeStamp) {
		super();
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}
	
	//Getters and Setters
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	

}
