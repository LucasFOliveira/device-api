package com.example.device.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.device.model.Device;
import com.example.device.repository.DeviceRepository;

@Configuration
public class LoadDatabase {
	
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(DeviceRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new Device("Smart TV", "LG")));
			log.info("Preloading " + repository.save(new Device("Soundbar", "Samsung")));
		};
	}
}
