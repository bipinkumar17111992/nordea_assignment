package com.nordeapackage.runner;

import com.nordeapackage.service.FileProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class FileProcessorRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(FileProcessorRunner.class);

    private final FileProcessorService fileProcessorService;
    //Constructor dependency here is used as its best compare to remaining two dependency injection method
@Autowired
    public FileProcessorRunner(FileProcessorService fileProcessorService) {
        this.fileProcessorService = fileProcessorService;
    }

    @Override
    public void run(String... args) {
        try {
            logger.info("Starting file processing...");
            fileProcessorService.processFiles();
            logger.info("File processing completed successfully.");
        } catch (Exception e) {
            logger.error("Error during file processing", e);
        }
    }
}
