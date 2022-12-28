package io.github.tlobato.mscreditappraiser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MscreditappraiserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscreditappraiserApplication.class, args);
	}

}
