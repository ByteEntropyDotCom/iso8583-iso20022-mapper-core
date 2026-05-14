package com.byteentropy.iso8583_iso20022_mapper_core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // Force the JSON converter to the very beginning of the list
        // This ensures JSON is always checked BEFORE XML for @RequestBody
        converters.add(0, new MappingJackson2HttpMessageConverter());
    }
}