How to Deploy a Spring Boot Application on Tomcat as a WAR Package:
**https://www.youtube.com/watch?v=05EKZ9Xmfws
https://www.youtube.com/watch?v=6RrqytT0-I8

We can deploy our application to AWS using amazon services like CodeStar or Elastic-Beanstalk. Every time we create a new project using service CodeStar, a new instance is created which is chargeable.
Or We can also create EC2 instance first and then access it using SSH. Then install java, tomcat using ssh commands. Now open tomcat UI manager console and deploy the web application.

SpringBoot .war file with embedded tomcat container can be run by just executing command "java -jar <war-file-name.war>". This is similar how we execute .jar file. So no external container is required.
We need to extend SpringBootServletInitializer when we want to deploy our web app to external container and not to embedded container like embedded tomcat.
SpringBootServletInitializer is an opinionated WebApplicationInitializer to run a SpringApplication from a traditional 
WAR deployment. Binds Servlet, Filter and ServletContextInitializer beans from the application context to the server. 
Note that a WebApplicationInitializer is only needed if you are building a war file and deploying it. If you prefer 
to run an embedded web server then you won't need this at all.

Server properties (like server.port) in application.properties come into picture when deploying application to 
embedded container/server. For external container, these server properties have no effect. So for external container, 
the application will not run on port mentioned in application.properties.

With embedded containers(like tomcat) the application's launch URL is "host:port", like http://localhost:8080.
But with external container, the application will be served at URL "host:port/<artifactId>", like http://localhost:8080/aws-web-app

Since we need to deploy this web app to external tomcat server, we need to exclude tomcat dependency which is by default added with spring-boot-starter-web. To exclude embedded tomcat, we can either exclude it from spring-boot-starter-web using <exclusion> tag or we need to mention tomcat's scope as provided as separate dependency. This is done to avoid conflict of embedded tomcat and external tomcat.
However, if we mention tomcat's scope as provided, the embedded tomcat is still there and we can run our web-app using embedded tomcat by executing command "java -jar <war-file-name.war>"
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-tomcat</artifactId>
	<scope>provided</scope>
</dependency>