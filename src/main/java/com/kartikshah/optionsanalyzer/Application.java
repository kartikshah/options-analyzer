package com.kartikshah.optionsanalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kartik on 7/8/16.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer
{
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
