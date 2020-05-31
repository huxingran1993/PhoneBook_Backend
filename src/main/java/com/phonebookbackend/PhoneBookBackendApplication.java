package com.phonebookbackend;

import com.phonebookbackend.repository.RoleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = RoleRepository.class)
public class PhoneBookBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneBookBackendApplication.class, args);
    }

}
