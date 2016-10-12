package org.spring.rest.api.example.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.MultipartConfigElement;

@Configuration
public class MultipartResolverConfiguration {

    @Value("${org.spring.rest.api.example.fileUpload.maxFileSize:128KB}")
    private String maxFileSize;

    @Value("${org.spring.rest.api.example.fileUpload.maxRequestSize:128KB}")
    private String maxRequestSize;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        final MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
        multipartConfigFactory.setMaxFileSize(maxFileSize);
        multipartConfigFactory.setMaxRequestSize(maxRequestSize);

        return multipartConfigFactory.createMultipartConfig();
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

}
