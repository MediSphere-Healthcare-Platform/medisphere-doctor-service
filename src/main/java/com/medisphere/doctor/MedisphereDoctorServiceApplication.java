package com.medisphere.doctor;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MedisphereDoctorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedisphereDoctorServiceApplication.class, args);
	}

}
