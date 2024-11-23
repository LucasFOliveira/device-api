package com.example.device.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.device.exception.DeviceNotFoundException;
import com.example.device.model.Device;
import com.example.device.repository.DeviceRepository;

@RestController
public class DeviceController {
	
	private final DeviceRepository repository;

	DeviceController(DeviceRepository repository) {
		this.repository = repository;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/devices")
	List<Device> all() {
		return repository.findAll();
	}
	// end::get-aggregate-root[]

	@PostMapping("/devices")
	Device newDevice(@RequestBody Device newDevice) {
		return repository.save(newDevice);
	}

	// Single item
	  
	@GetMapping("/devices/{id}")
	Device one(@PathVariable Long id) {   
		return repository.findById(id).orElseThrow(() -> new DeviceNotFoundException(id));
	}
	
	@GetMapping("devices/searchByBrand")
    public List<Device> searchDevices(String brand) {
        List<Device> devices = repository.findByBrandContainingIgnoreCase(brand);
        return devices.stream().toList();
    }

	@PutMapping("/devices/{id}")
	Device replaceDevice(@RequestBody Device newDevice, @PathVariable Long id) {
		return repository.findById(id)
			.map(device -> {
				device.setName(newDevice.getName());
				device.setBrand(newDevice.getBrand());
				return repository.save(device);
			}).orElseGet(() -> {
				return repository.save(newDevice);
			});
	}

	@DeleteMapping("/devices/{id}")
	void deleteDevice(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
