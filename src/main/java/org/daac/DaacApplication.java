package org.daac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EntityScan("org.daac.pojo")
//@ComponentScan(basePackages = {"org.daac.sw.dao","org.daac.sw.controller","org.daac.sw.service"})
public class DaacApplication{
	
	public static void main(String[] args) {
		System.setProperty("java.net.preferIPv4Stack" , "true");
		SpringApplication.run(DaacApplication.class, args);
	}
	
}
