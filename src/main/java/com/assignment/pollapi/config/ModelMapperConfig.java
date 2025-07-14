package com.assignment.pollapi.config;




import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper dozerMapper() {
        return new ModelMapper(); // uses default empty mapping
    }
}
