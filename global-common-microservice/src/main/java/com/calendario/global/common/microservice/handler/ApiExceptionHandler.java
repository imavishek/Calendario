/**
 * @ProjectName: global-common-microservice
 * @PackageName: com.calendario.global.common.microservice.handler
 * @FileName: ApiExceptionHandler.java
 * @Author: Avishek Das
 * @CreatedDate: 02-04-2020
 * @Modified_By avishekdas @Last_On 02-Apr-2020 3:39:08 pm
 */

package com.calendario.global.common.microservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.calendario.global.common.microservice.dto.Response;
import com.calendario.global.common.microservice.util.ResponseUtil;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

	/**
	 * Method is used to handle NoHandlerFoundException.
	 * 
	 * @param e : NoHandlerFoundException class object.
	 * @return ResponseEntity : Object containing the response status and Response
	 *         object.
	 */
	@ExceptionHandler({ NoHandlerFoundException.class })
	public ResponseEntity<Response<Object>> handlerNoHandlerFoundException(NoHandlerFoundException e) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		String message = "The page you are looking for might have been removed had its name changed or is temporarily unavailable.";

		Response<Object> response = ResponseUtil.getResponse(status.value(), "Not Found", message, null);

		log.error(response.toString());

		return new ResponseEntity<>(response, status);
	}

	/**
	 * Method is used to handle generic Exception.
	 * 
	 * @param e : Exception class object.
	 * @return ResponseEntity : Object containing the response status and Response
	 *         object.
	 */
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Response<Object>> handleException(Exception e) {

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String message = "The server has encountered an unexpected error. Please contact administrator.";

		Response<Object> response = ResponseUtil.getResponse(status.value(), "Internal Server Error", message, null);

		log.error(e.getMessage() + " [Exception " + e.getClass() + "]");

		return new ResponseEntity<>(response, status);
	}
}
