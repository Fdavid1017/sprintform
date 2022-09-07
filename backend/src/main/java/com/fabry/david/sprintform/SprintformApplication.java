package com.fabry.david.sprintform;

import com.fabry.david.sprintform.domains.Transaction;
import com.fabry.david.sprintform.enums.TransactionCategory;
import com.fabry.david.sprintform.repositories.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.util.Date;
import java.util.List;

@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class SprintformApplication implements ApplicationRunner {

    private final TransactionRepository transactionRepository;

    public static void main(String[] args) {
        SpringApplication.run(SprintformApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        if (transactionRepository.count() == 0) {
            System.out.println("Filling base data");

            transactionRepository.save(new Transaction(1L, "Base data 1", TransactionCategory.FINANCIAL, 1200F, "HUF", new Date()));
            transactionRepository.save(new Transaction(2L, "Base data 2", TransactionCategory.HOUSING, 900F, "HUF", new Date()));
        } else {
            System.out.println("Database already has data");
        }
    }
}
