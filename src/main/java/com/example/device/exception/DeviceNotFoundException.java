package com.example.device.exception;

public class DeviceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5401307550404634951L;

	public DeviceNotFoundException(Long id) {
		super("Could not find device " + id);
	}
}
