package com.Hackathon.probStatement4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan
@EnableJpaRepositories
public class HackathonProbStatement4Application {

	public static void main(String[] args) {
		SpringApplication.run(HackathonProbStatement4Application.class, args);
	}

}
