package com.springcourse.course.SpringCourse.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError{
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<FieldMessage>(); 

	public ValidationError(Integer status, String message, long timeStamp) {
		super(status, message, timeStamp);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}
	
	
	

}
