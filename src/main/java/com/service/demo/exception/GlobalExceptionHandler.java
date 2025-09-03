package com.service.demo.exception;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleNullPointerException(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(Exception e) {
		log.error("GlobalExceptionHandler :: handleResourceNotFoundException ::", e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

		List<ObjectError> allerrors = e.getBindingResult().getAllErrors();

		Map<String, Object> error = new LinkedHashMap<>();
		allerrors.stream().forEach(er -> {
			String msg = er.getDefaultMessage();
			String field = ((FieldError) (er)).getField();
			error.put(field, msg);

		});

		log.error("GlobalExceptionHandler :: handleMethodArgumentNotValidException ::", e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> handleValidationException(ValidationException e) {
		log.error("GlobalExceptionHandler :: handleValidationException ::", e.getMessage());
		return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ExitDetaException.class)
	public ResponseEntity<?> handleExitDetaException(ExitDetaException e) {
		log.error("GlobalExceptionHandler :: handleExitDetaException ::", e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
	}

}
