package com.example.device.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.device.exception.DeviceNotFoundException;
import com.example.device.model.Device;
import com.example.device.service.DeviceService;

@RequestMapping("/devices")
@RestController
public class DeviceController {
	
	@Autowired
	private DeviceService deviceService;

	DeviceController() {}

	@GetMapping()
	List<Device> allDevices() {
		List<Device> devices = deviceService.list();
		return devices.stream().toList();
	}

	@PostMapping()
	ResponseEntity<Device> newDevice(@RequestBody Device newDevice) {
		Device device = deviceService.create(new Device(newDevice.getName(), newDevice.getBrand()));
		return ResponseEntity.ok(device);
	}

	@GetMapping("/{id}")
	ResponseEntity<Device> oneDevice(@PathVariable Long id) { 
		Optional<Device> device = deviceService.read(id);
        if (device.isPresent()) {
            return ResponseEntity.ok(new Device(device.get().getId(), device.get().getName(), device.get().getBrand(), device.get().getCreation()));
        }
        throw new DeviceNotFoundException(id);
	}
	
	@GetMapping("/searchByBrand")
    List<Device> searchDeviceByBrand(String brand) {
        List<Device> devices = deviceService.searchDeviceByBrand(brand);
        return devices.stream().toList();
    }

	@PutMapping("/{id}")
	Device updateDevice(@RequestBody Device newDevice, @PathVariable Long id) {
		return deviceService.update(newDevice, id);
	}

	@DeleteMapping("/{id}")
	void deleteDevice(@PathVariable Long id) {
		deviceService.delete(id);
	}
}
