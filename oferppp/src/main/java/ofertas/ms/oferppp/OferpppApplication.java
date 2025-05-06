package ofertas.ms.oferppp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OferpppApplication {

	public static void main(String[] args) {
		SpringApplication.run(OferpppApplication.class, args);
	}

}
