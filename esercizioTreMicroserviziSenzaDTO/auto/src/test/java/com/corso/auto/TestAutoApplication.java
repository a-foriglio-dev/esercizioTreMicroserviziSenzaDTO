package com.corso.auto;

import org.springframework.boot.SpringApplication;

public class TestAutoApplication {

	public static void main(String[] args) {
		SpringApplication.from(AutoApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
