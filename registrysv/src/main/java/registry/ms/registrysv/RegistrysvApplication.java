package registry.ms.registrysv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistrysvApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrysvApplication.class, args);
	}

}
