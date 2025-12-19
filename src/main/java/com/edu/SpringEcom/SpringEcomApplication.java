package com.edu.SpringEcom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Spring E-Commerce application.
 * 
 * <p>
 * This class bootstraps the Spring Boot application using
 * {@link SpringBootApplication} which enables:
 * <ul>
 * <li>Auto-configuration based on classpath</li>
 * <li>Component scanning in this package and sub-packages</li>
 * <li>Configuration properties support</li>
 * </ul>
 * </p>
 * 
 * @author SpringEcom Team
 * @version 1.0
 * @since 2025-12-14
 */
@SpringBootApplication
public class SpringEcomApplication {

	/**
	 * Application entry point.
	 * 
	 * @param args command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringEcomApplication.class, args);
	}

}
