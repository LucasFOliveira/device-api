package com.example.device.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.device.model.Device;
import com.example.device.repository.DeviceRepository;

@Service
public class DeviceService {
	
	@Autowired
    private DeviceRepository deviceRepository;
	
	public List<Device> list() {
        return deviceRepository.findAll();
    }

    public Optional<Device> read(Long id) {
        return deviceRepository.findById(id);
    }

    public List<Device> searchDeviceByBrand(String brand) {
        return deviceRepository.findByBrandContainingIgnoreCase(brand);
    }

    public Device create(Device device) {
        return deviceRepository.save(device);
    }
    
    public void delete(Long id) {
    	deviceRepository.deleteById(id);
    }
    
    public Device update(Device newDevice, Long id) {
    	return deviceRepository.findById(id)
			.map(device -> {
				device.setName(newDevice.getName());
				device.setBrand(newDevice.getBrand());
				device.setCreation(new Date());
				return deviceRepository.save(device);
			}).orElseGet(() -> {
				return deviceRepository.save(newDevice);
			});
    }
}
