package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * SpringBoot .war file with embedded tomcat container can be run by just executing command "java -jar <war-file-name.war>". This is similar how we execute .jar file. So no external container is required.
 *  
 * We need to extend SpringBootServletInitializer when we want to deploy our web app to external container and not to embedded container like embedded tomcat.
 * SpringBootServletInitializer is an opinionated WebApplicationInitializer to run a SpringApplication from a traditional 
 * WAR deployment. Binds Servlet, Filter and ServletContextInitializer beans from the application context to the server. 
 * Note that a WebApplicationInitializer is only needed if you are building a war file and deploying it. If you prefer 
 * to run an embedded web server then you won't need this at all.
 * 
 * server properties (like server.port) in application.properties come into picture when deploying application to 
 * embedded container/server. For external container, these server properties have no effect. So for external container, 
 * the application will not run on port mentioned in application.properties
 */
@SpringBootApplication
//@CrossOrigin(origins = "*")
public class AwsWebAppApplication {
/*Here we need not to extend SpringBootServletInitializer because SpringBoot have already created a java file named 
	ServletInitializer which extends SpringBootServletInitializer and overrides configure method.*/
//public class AwsWebAppApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AwsWebAppApplication.class, args);
	}

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(AwsWebAppApplication.class);
//	}
	
//	@Bean(name = "multipartResolver")
//	public CommonsMultipartResolver multipartResolver() {
//	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//	    multipartResolver.setMaxUploadSize(100000);
//	    return multipartResolver;
//	}
}
