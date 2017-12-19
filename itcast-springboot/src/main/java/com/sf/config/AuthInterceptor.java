package com.sf.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sf.interceptor.UserInterceptor;



@Configuration
public class AuthInterceptor extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new UserInterceptor()).addPathPatterns("/user/**");
		super.addInterceptors(registry);
	}

}
