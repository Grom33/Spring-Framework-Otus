package ru.otus.gromov.configuration;

/*
 *   Created by Gromov Vitaly (Grom33), 2018
 *   e-mail: mr.gromov.vitaly@gmail.com
 */

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HystrixConfiguration {

	@Bean(name = "hystrixConfig")
	public HystrixCommand.Setter getHystrixConfig() {

		HystrixCommand.Setter config = HystrixCommand
				.Setter
				.withGroupKey(HystrixCommandGroupKey.Factory.asKey("DataBaseCircuitBreaker"));

		HystrixCommandProperties.Setter properties = HystrixCommandProperties.Setter();
		properties.withExecutionTimeoutInMilliseconds(1000);
		properties.withCircuitBreakerSleepWindowInMilliseconds(4000);
		properties.withExecutionIsolationStrategy
				(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD);
		properties.withCircuitBreakerEnabled(true);
		properties.withCircuitBreakerRequestVolumeThreshold(1);

		config.andCommandPropertiesDefaults(properties);
		config.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
				.withMaxQueueSize(1)
				.withCoreSize(1)
				.withQueueSizeRejectionThreshold(1));

		return config;
	}

}
