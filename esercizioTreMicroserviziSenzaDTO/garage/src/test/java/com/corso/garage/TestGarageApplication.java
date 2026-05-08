package com.corso.garage;

import org.springframework.boot.SpringApplication;

public class TestGarageApplication {

	public static void main(String[] args) {
		SpringApplication.from(GarageApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
