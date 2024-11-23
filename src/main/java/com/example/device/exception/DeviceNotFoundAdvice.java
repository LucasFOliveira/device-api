package com.example.device.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class DeviceNotFoundAdvice {

	@ExceptionHandler(DeviceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String deviceNotFoundHandler(DeviceNotFoundException ex) {
		return ex.getMessage();
	}
}
