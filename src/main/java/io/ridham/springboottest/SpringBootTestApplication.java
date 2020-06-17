package io.ridham.springboottest;

import io.ridham.springboottest.model.ResponseObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringBootTestApplication {

//    private static final Logger log = LoggerFactory.getLogger(SpringBootTestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

//    @Bean
//    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
//        return args -> {
//            ResponseObj responseObj1 = restTemplate.getForObject(
//                    "http://www.mocky.io/v2/5ecfd5dc3200006200e3d64b", ResponseObj.class);
//            log.info(responseObj1.toString());
//        };
//    }
}
