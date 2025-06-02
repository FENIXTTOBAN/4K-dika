package upeu.edu.pe.gateway.beans;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Set;

@Configuration
public class GatewayBeans {
    //config routes level basic
    @Bean
    @Profile(value = "eureka-off")
    public RouteLocator routeLocatorEurekaOff (RouteLocatorBuilder builder){
        return builder
                .routes()
                .route(route -> route
                        .path("/ofertapostulacion-ms/postulacion/*")
                        .uri("http://localhost:8081")
                )
                .route(route -> route
                        .path("/personas-ms/persona/*")
                        .uri("http://localhost:8082")
                )
                .route(route -> route
                        .path("/practicasevidencias-ms/practica/*")
                        .uri("http://localhost:8083")
                )
                .build();
    }
    @Bean
    @Profile(value = "eureka-on")
    public RouteLocator routeLocatorEurekaOn (RouteLocatorBuilder builder){
        return builder
                .routes()
                .route(route -> route
                        .path("/ofertapostulacion-ms/postulacion/**")
                        .uri("lb://ofertapostulacion-ms")  //load balance = lb = balanceo de carga
                )
                .route(route -> route
                        .path("/personas-ms/persona/**")
                        .uri("lb://personas-ms")  //load balance = lb = balanceo de carga
                )
                .route(route -> route
                        .path("/practicasevidencias-ms/practica/**")
                        .uri("lb://practicasevidencias-ms")  //load balance = lb = balanceo de carga
                )
                .build();
    }




}
