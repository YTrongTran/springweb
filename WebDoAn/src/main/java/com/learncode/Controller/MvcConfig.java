package com.learncode.Controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configurable
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
//		WebMvcConfigurer.super.addResourceHandlers(registry);
		Path UploadDir = Paths.get("./uploads/");
		String UploadPath =  UploadDir.toFile().getAbsolutePath();	
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:/" + UploadPath + "/");
	}

	
}
