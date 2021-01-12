package net.springboot.blog;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;


@SpringBootApplication
public class SpringbootBlog {

    public static void main(String[] args) {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasenames("lang/res");
//        System.out.println(messageSource.getMessage("hello", null , Locale.ENGLISH));
        SpringApplication.run(SpringbootBlog.class, args);
    }
}
