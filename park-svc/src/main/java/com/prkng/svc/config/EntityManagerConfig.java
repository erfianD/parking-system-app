package com.prkng.svc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Configuration
public class EntityManagerConfig {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public EntityManager entityManager() {
        return entityManager;
    }
}
