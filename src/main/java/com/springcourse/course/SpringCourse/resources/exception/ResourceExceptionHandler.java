package com.springcourse.course.SpringCourse.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springcourse.course.SpringCourse.services.exception.DataIntegrityException;
import com.springcourse.course.SpringCourse.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandartError> ObjectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		StandartError error = new StandartError(HttpStatus.NOT_FOUND.value(),
												e.getMessage(),
												System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandartError> dataIntregity(DataIntegrityException e, HttpServletRequest request){
		StandartError error = new StandartError(HttpStatus.BAD_REQUEST.value(),
												e.getMessage(),
												System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
	

}
