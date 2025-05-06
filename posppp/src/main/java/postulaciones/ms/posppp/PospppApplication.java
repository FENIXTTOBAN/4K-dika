package postulaciones.ms.posppp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PospppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PospppApplication.class, args);
	}

}
