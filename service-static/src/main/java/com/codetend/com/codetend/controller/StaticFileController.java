package com.codetend.com.codetend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@ConfigurationProperties(prefix = "com.codetend")
public class StaticFileController {
    private static final Logger log = LoggerFactory.getLogger(StaticFileController.class);
    private final ResourceLoader resourceLoader;

    public StaticFileController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    @GetMapping("/first/{fileName}")
    public ResponseEntity<Resource> first(@PathVariable String fileName) {
        log.info(String.format("GET first/%s", fileName));
        return getFile("first", fileName);
    }

    @GetMapping("/second/{fileName}")
    public ResponseEntity<Resource> second(@PathVariable String fileName) {
        log.info(String.format("GET second/%s", fileName));
        return getFile("second", fileName);
    }

    private ResponseEntity<Resource> getFile(String folder, String fileName) {
        Resource resource = resourceLoader.getResource(String.format("classpath:/static/%s/%s", folder, fileName));

        // If the file doesn't exist, you may want to serve a default file
        if (!resource.exists()) {
            resource = resourceLoader.getResource(String.format("classpath:/static/%s/index.html", folder));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML); // You may change the content type based on your file type

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
