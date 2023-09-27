package org.sisvir.msvc.devices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcDevicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcDevicesApplication.class, args);
	}

}
