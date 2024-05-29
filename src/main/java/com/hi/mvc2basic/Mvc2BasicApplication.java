package com.hi.mvc2basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableScheduling
//@SpringBootApplication(exclude = {QuartzAutoConfiguration.class})
//@ServletComponentScan
@SpringBootApplication
public class Mvc2BasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(Mvc2BasicApplication.class, args);
    }

//    @Bean
//    public MessageSource messageSource(){
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasenames("messages", "errors");
//        messageSource.setDefaultEncoding("utf-8");
//        return messageSource;
//    }

}
