package ru.otus.gromov.configuration;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ru.otus.gromov.service.HeavenService;
import ru.otus.gromov.service.HellService;
import ru.otus.gromov.service.MorgueService;
import ru.otus.gromov.service.World;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Configuration
public class actuatorConfig {
	private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

	@Bean
	ApplicationRunner runner(MeterRegistry mr, HeavenService heavenService) {
		return args -> this.executorService.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				mr.timer("pray-duration").record(Duration.ofMillis((heavenService.getPrayDuration())));
			}
		}, 1000, 1000, TimeUnit.MILLISECONDS);
	}

	@Component
	@AllArgsConstructor
	public class SinHealthIndicator implements HealthIndicator {
		private final HeavenService heavenService;

		@Override
		public Health health() {
			return (isSinfulWorld()) ? Health.down().withDetail("Error", "World goes to Hell").build() : Health.up().build();
		}

		private boolean isSinfulWorld() {
			return heavenService.getParadisePopulation() <= 0;
		}
	}

	@Component
	@Endpoint(id = "worlds")
	@AllArgsConstructor
	public class FeaturesEndpoint {
		private Map<String, World> worlds = new ConcurrentHashMap<>();

		@ReadOperation
		public Map<String, World> worlds() {
			return worlds;
		}

		@ReadOperation
		public String getSlogan(@Selector String name) {
			return worlds.get(name).getSlogan();
		}

		@WriteOperation
		public void setSlogan(@Selector String name, String slogan) {
			worlds.get(name).setSlogan(slogan);
		}
	}

	@Component
	@AllArgsConstructor
	public class ExampleInfoContributor implements InfoContributor {

		private final Map<String, World> worldMap;

		@Override
		public void contribute(Info.Builder builder) {
			builder.withDetail("World-chiefs", getChiefs());
		}

		private Map<String, String> getChiefs() {
			Map<String, String> map = new HashMap<>();
			worldMap.forEach((k, v) -> map.put(k, v.getChiefName()));
			return map;
		}
	}

}