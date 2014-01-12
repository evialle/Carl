package org.vialle.carl.speech.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by eric on 29/12/2013.
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.vialle.carl"})
public class Application {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext cac = SpringApplication.run(Application.class, args);

    }
}
