package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student mariam = new Student(
                    1l,
                    "mariam",
                    "asd@gmail.com",
                    LocalDate.of(2000, Month.APRIL,2)

            );
            Student pablo = new Student(
                    "pablo",
                    "pablo@gmail.com",
                    LocalDate.of(2004, Month.APRIL,2)

            );

            repository.saveAll(
                    List.of(mariam,pablo)
            );

        };
    }
}
