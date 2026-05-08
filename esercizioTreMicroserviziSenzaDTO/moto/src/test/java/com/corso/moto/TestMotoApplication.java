package com.corso.moto;

import org.springframework.boot.SpringApplication;

public class TestMotoApplication {

	public static void main(String[] args) {
		SpringApplication.from(MotoApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
