package unir.tfg.fuxpin.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *
 * Eureka Netflix Server for Fuxpin Applications
 *
 * Black Modernitzation - Functional Wrapping - Microservices Layer
 *
 * @author Xavier Rodríguez
 *
 */

@SpringBootApplication
@EnableEurekaServer
public class EurekaserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaserverApplication.class, args);
	}

}
