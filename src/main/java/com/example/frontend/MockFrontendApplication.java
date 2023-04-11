package com.example.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.front.entites.ConvertListService;
import com.example.front.entites.EmailService;

@SpringBootApplication
public class MockFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockFrontendApplication.class, args);
	}

	@Bean 
    public MailProperties mailProperties() {
        return new MailProperties();
    }
	@Bean 
    public EmailService emailService() {
        return new EmailService();
    }
	@Bean 
    public ConvertListService convertListService() {
		return new ConvertListService();
	}
	
}
