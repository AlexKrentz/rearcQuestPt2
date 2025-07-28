package com.example;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.service.PopulationDataSyncService;

import java.io.IOException;

@SpringBootApplication
public class RearcQuest2 {
    @Autowired
    PopulationDataSyncService populationDataSyncService;

    public static void main(String[] args) {
        SpringApplication.run(RearcQuest2.class, args);
    }

    @PostConstruct
    public void runOnStartup() throws IOException {
        populationDataSyncService.syncDataToS3();
    }
}
