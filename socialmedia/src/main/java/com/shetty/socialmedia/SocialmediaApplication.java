package com.shetty.socialmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Shetty Media", version = "1.0", description = "RestAPI for social media applications", contact = @Contact(name = "Dhanush Shetty",email = "dhanushdinesh29@gmail.com", url = "https://github.com/Dhanush-D-Shetty/SocialMediaAPI")))
public class SocialmediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialmediaApplication.class, args);
	}
}
 
