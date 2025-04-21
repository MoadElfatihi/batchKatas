package com.batchkatas.batchkatas;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class BatchkatasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchkatasApplication.class, args);
	}

}
