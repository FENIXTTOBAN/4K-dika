package www.sistemaspracticas.report_ms.repositories;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ofertapostulacion-ms")
@LoadBalancerClient(name = "ofertapostulacion-ms", configuration = LoadBalancerClientConfiguration.class)
public interface OfertapostulacionRepository {
}
