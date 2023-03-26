package com.michel.reliability;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.michel.reliability.utils.Constants;

@Configuration
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler(Constants.RESSOURCE_FOLDER)
		.addResourceLocations(Constants.RESSOURCE_LOCATION);
	}
	
	 @Override
	   public void addCorsMappings(CorsRegistry registry) {
	       registry.addMapping("/**");
	   }
	

}
