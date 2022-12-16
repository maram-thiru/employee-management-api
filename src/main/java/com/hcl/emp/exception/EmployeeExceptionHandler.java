package com.hcl.emp.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler  {

	@Value(value = "${exception.message.notfound}")
	private String exceptionMsgNotFound;

	@Value(value = "${exception.message.already.exist}")
	private String exceptionMsgAlreadyExist;

	@Value(value = "${exception.message.server.error}")
	private String exceptionMsgServerError;


	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public ResponseEntity employeeNotFoundException(EmployeeNotFoundException employeeNotFoundException) {
		return new ResponseEntity(exceptionMsgNotFound, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> serverException(Exception exception) {
		return new ResponseEntity<>(exceptionMsgServerError, HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
