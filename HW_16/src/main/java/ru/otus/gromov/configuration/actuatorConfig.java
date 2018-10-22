package ru.otus.gromov.configuration;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*https://www.baeldung.com/spring-boot-actuators*/

@Configuration
public class actuatorConfig {


	/*@Bean
	public SecurityWebFilterChain securityWebFilterChain(
			ServerHttpSecurity http) {
		return http.authorizeExchange()
				.pathMatchers("/actuator/**").permitAll()
				.anyExchange().authenticated()
				.and().build();
	}*/


	@Component
	public class SinIndicator implements HealthIndicator {

		@Override
		public Health health() {
			return checkDownstreamServiceHealth();
		}

		private Health checkDownstreamServiceHealth() {
			return new Health.Builder().up().build();
		}
	}


	@Component
	@Endpoint(id = "features")
	public class FeaturesEndpoint {
/*
		private Map<String, Integer> features = new ConcurrentHashMap<>();

		@ReadOperation
		public Map<String, Feature> features() {
			return features;
		}

		@ReadOperation
		public Feature feature(@Selector String name) {
			return features.get(name);
		}*/

		/*@WriteOperation
		public void configureFeature(@Selector String name, Feature feature) {
			features.put(name, feature);
		}

		@DeleteOperation
		public void deleteFeature(@Selector String name) {
			features.remove(name);
		}*/

	}



}
