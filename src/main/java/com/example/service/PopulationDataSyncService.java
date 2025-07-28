package com.example.service;

import com.example.client.UsaDataApiClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PopulationDataSyncService {

    private static final Logger logger = LogManager.getLogger(PopulationDataSyncService.class);
    private final UsaDataApiClient usaDataApiClient;
    private final S3Client s3Client;
    private static final String S3_BUCKET_NAME = "rearcquestapibucket";

    public PopulationDataSyncService(UsaDataApiClient usaDataApiClient, S3Client s3Client) {
        this.usaDataApiClient = usaDataApiClient;
        this.s3Client = s3Client;
    }


        public void syncDataToS3() throws IOException {
        String responseData = usaDataApiClient.callUsaDataApi();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        Path tempFile = Files.createTempFile("population-data-" + timestamp, ".json");
        Files.writeString(tempFile, responseData);

        String s3Key = "population/" + tempFile.getFileName();

        s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(S3_BUCKET_NAME)
                        .key(s3Key)
                        .build(),
                tempFile
        );

        logger.info("Uploaded to S3: {}", s3Key);
    }
}
