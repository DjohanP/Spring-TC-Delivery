package com.pbkk.finalproject.tcdelivery;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Controller
public class TcDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TcDeliveryApplication.class, args);
	}
	
	@ResponseBody
	@RequestMapping("/")
	public String sayAloha(){
		return "Aloha";
	}
	
	@RequestMapping("/public-key")
	public ResponseEntity<?> getFile( 
			HttpServletResponse response) throws Exception{
		
		File file = new ClassPathResource("key/jwtRS256.key.pub").getFile();
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                  "attachment;filename=" + file.getName())
            .contentType(MediaType.TEXT_PLAIN).contentLength(file.length())
            .body(resource);
	}
}
