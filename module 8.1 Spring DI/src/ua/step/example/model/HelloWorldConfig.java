package ua.step.example.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Конфигурация создания бина с использованием анотаций 
 * 
 */
@Configuration
public class HelloWorldConfig {
   @Bean 
   @Scope("prototype")
   public HelloWorld helloWorld(){
      return new HelloWorld();
   }
}