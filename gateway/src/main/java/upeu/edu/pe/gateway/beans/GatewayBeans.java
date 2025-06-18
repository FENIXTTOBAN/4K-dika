package upeu.edu.pe.gateway.beans;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import upeu.edu.pe.gateway.filters.AuthFilter;

import java.util.Set;

@Configuration
public class GatewayBeans {

    private final AuthFilter authFilter;

    public GatewayBeans(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }
    //config routes level basic
    @Bean
    @Profile(value = "eureka-off")
    public RouteLocator routeLocatorEurekaOff (RouteLocatorBuilder builder){
        return builder
                .routes()
                .route(route -> route
                        .path("/personas-ms/personas/*")
                        .uri("http://localhost:8081")
                )
                .route(route -> route
                        .path("/ofertapostulacion-ms/postulacion/*")
                        .uri("http://localhost:8082")
                )
                .route(route -> route
                        .path("/practicasevidencias-ms/practica/*")
                        .uri("http://localhost:8083")
                )
                .route(route -> route
                        .path("/auth-server/**")
                        .uri("http://localhost:3030")
                )
                .build();
    }
    @Bean
    @Profile(value = "eureka-on")
    public RouteLocator routeLocatorEurekaOn (RouteLocatorBuilder builder){
        return builder
                .routes()
                .route(route -> route
                        .path("/ofertapostulacion-ms/**")
                        .uri("lb://ofertapostulacion-ms")  //load balance = lb = balanceo de carga
                )
                .route(route -> route
                        .path("/personas-ms/personas/**")
                        .uri("lb://personas-ms")  //load balance = lb = balanceo de carga
                )
                .route(route -> route
                        .path("/practicasevidencias-ms/practica/**")
                        .uri("lb://practicasevidencias-ms")  //load balance = lb = balanceo de carga
                )
                .route(route -> route
                        .path("/auth-server/**")
                        .uri("lb://auth-server")
                )
                .build();
    }

    @Bean
    @Profile(value = "oauth2")
    public RouteLocator routeLocatorOauth2 (RouteLocatorBuilder builder){
        return builder
                .routes()
                .route(route -> route
                        .path("/ofertapostulacion-ms/**")
                        .filters(filter -> filter.filter(this.authFilter))
                        .uri("lb://ofertapostulacion-ms")  //load balance = lb = balanceo de carga
                )
                .route(route -> route
                        .path("/personas-ms/personas/**")
                        .filters(filter -> filter.filter(this.authFilter))
                        .uri("lb://personas-ms")
                )
                .route(route -> route
                        .path("/practicasevidencias-ms/**")
                        .filters(filter -> filter.filter(this.authFilter))
                        .uri("lb://practicasevidencias-ms")

                )
                .route(route -> route
                        .path("/auth-server/auth/**")
                        .uri("lb://auth-server")
                )
                .build();
    }



}
