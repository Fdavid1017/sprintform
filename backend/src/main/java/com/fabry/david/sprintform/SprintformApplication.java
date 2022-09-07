package com.fabry.david.sprintform;

import com.fabry.david.sprintform.domains.Transaction;
import com.fabry.david.sprintform.repositories.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class SprintformApplication implements ApplicationRunner {

    private final TransactionRepository transactionRepository;

    public static void main(String[] args) {
        SpringApplication.run(SprintformApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(ApplicationArguments args) {
        if (transactionRepository.count() == 0) {
            System.out.println("Filling base data");

            try {
                Transaction[] transactions = loadBaseTransactions();

                for (Transaction transaction : transactions) {
                    transactionRepository.save(transaction);
                }
            } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
                System.out.println("Error while loading base datas");
                System.out.println("==============================");
                e.printStackTrace();
            }
        } else {
            System.out.println("Database already has data");
        }
    }

    private Transaction[] loadBaseTransactions() throws JsonProcessingException {
        final String uri = "https://development.sprintform.com/transactions.json";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(result, Transaction[].class);
    }
}
