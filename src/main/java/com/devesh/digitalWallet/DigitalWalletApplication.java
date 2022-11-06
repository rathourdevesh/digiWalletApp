package com.devesh.digitalWallet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
public class DigitalWalletApplication extends SpringBootServletInitializer {

    private static final Class<DigitalWalletApplication> applicationClass = DigitalWalletApplication.class;
    private static final Logger log = LoggerFactory.getLogger(applicationClass);

	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(applicationClass);
//    }
	
}
