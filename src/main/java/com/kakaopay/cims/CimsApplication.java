package com.kakaopay.cims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing 
public class CimsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CimsApplication.class, args);
	}

}
