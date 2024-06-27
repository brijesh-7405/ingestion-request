package com.ddf.ingestion_ddf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Entry point for the Ingestion DDF application.
 * Configures the Spring Boot application and sets up asynchronous processing for tasks.
 */
@SpringBootApplication
@EnableAsync
public class IngestionDdfApplication {

    /**
     * Main method to start the Spring Boot application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(IngestionDdfApplication.class, args);
    }

    /**
     * Configures a thread pool task executor bean for executing asynchronous tasks.
     *
     * @return The configured {@link Executor} bean.
     */
    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("AsyncEmail-");
        executor.initialize();
        return executor;
    }

}
