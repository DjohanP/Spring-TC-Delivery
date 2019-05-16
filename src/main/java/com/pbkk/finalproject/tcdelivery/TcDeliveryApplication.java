package com.pbkk.finalproject.tcdelivery;

import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Controller
public class TcDeliveryApplication {

	@Autowired
	ResourceLoader resourceLoader;
	
	byte[] buffer = new byte[8 * 1024];
	
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
		
		ServletOutputStream outputStream=response.getOutputStream();
		
        InputStream input = new ClassPathResource("key/jwtRS256.key.pub").getInputStream();
        byte[] bytes = IOUtils.toByteArray(input);

        outputStream.write(bytes, 0, bytes.length);
        
		return ResponseEntity.ok()
            .contentLength(bytes.length)
            .body(outputStream);
	}
}
