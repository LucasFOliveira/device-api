package com.example.device.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.device.exception.DeviceNotFoundException;
import com.example.device.model.Device;
import com.example.device.repository.DeviceRepository;

@RequestMapping("/devices")
@RestController
public class DeviceController {
	
	private final DeviceRepository repository;

	DeviceController(DeviceRepository repository) {
		this.repository = repository;
	}

	@GetMapping()
	List<Device> list() {
		return repository.findAll();
	}

	@PostMapping()
	Device create(@RequestBody Device newDevice) {
		return repository.save(newDevice);
	}

	@GetMapping("/{id}")
	Device read(@PathVariable Long id) {   
		return repository.findById(id).orElseThrow(() -> new DeviceNotFoundException(id));
	}
	
	@GetMapping("/searchByBrand")
    List<Device> searchByBrand(String brand) {
        List<Device> devices = repository.findByBrandContainingIgnoreCase(brand);
        return devices.stream().toList();
    }

	@PutMapping("/{id}")
	Device update(@RequestBody Device newDevice, @PathVariable Long id) {
		return repository.findById(id)
			.map(device -> {
				device.setName(newDevice.getName());
				device.setBrand(newDevice.getBrand());
				return repository.save(device);
			}).orElseGet(() -> {
				return repository.save(newDevice);
			});
	}

	@DeleteMapping("/{id}")
	void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
