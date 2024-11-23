package com.example.device.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.device.model.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {

}
