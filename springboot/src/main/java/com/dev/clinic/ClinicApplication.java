package com.dev.clinic;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.dev.clinic.service.FilesStorageService;

@EnableWebMvc
@SpringBootApplication
public class ClinicApplication implements CommandLineRunner {

    @Resource
    FilesStorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(ClinicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // storageService.init();

    }

}
