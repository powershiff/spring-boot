package com.sf.application;

import java.nio.charset.Charset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
@Configuration
public class HelloApplication {

	@RequestMapping("hello")
	@ResponseBody
	public String hello() {
		return "hello wolrd 外网";
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}
	
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter(){
		StringHttpMessageConverter converter=new StringHttpMessageConverter(Charset.forName("utf-8"));
		return   converter;
	}
	
}
