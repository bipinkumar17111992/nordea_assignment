package com.nordeapackage.service;

import com.nordeapackage.config.ParserProperties;
import com.nordeapackage.formatter.CsvFormatter;
import com.nordeapackage.formatter.XmlFormatter;
import com.nordeapackage.parser.TextParser;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileProcessorServiceTest {

    private FileProcessorService service;
    private Path outputFolder;

    @BeforeEach
    void setup() throws IOException {
        Path inputFolder = Files.createTempDirectory("input");
        outputFolder = Files.createTempDirectory("output");

        ParserProperties props = new ParserProperties();
        props.setInputFolder(inputFolder.toString());
        props.setOutputFolder(outputFolder.toString());

        service = new FileProcessorService(props, new TextParser(), new XmlFormatter(), new CsvFormatter());

        // Create sample input file
        Path inputFile = inputFolder.resolve("test.in");
        Files.writeString(inputFile, "Hello Spring Boot!");
    }

    @Test
    void testProcessFiles() throws IOException {
        service.processFiles();

        List<Path> outputs = Files.list(outputFolder).toList();
        assertEquals(2, outputs.size()); // Expect XML + CSV
    }
}
