package com.nordeapackage.service;

import com.nordeapackage.config.ParserProperties;
import com.nordeapackage.formatter.CsvFormatter;
import com.nordeapackage.formatter.XmlFormatter;
import com.nordeapackage.model.Sentence;
import com.nordeapackage.parser.TextParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
public class FileProcessorService {

    private static final Logger logger = LoggerFactory.getLogger(FileProcessorService.class);

    private final ParserProperties properties;
    private final TextParser parser;
    private final XmlFormatter xmlFormatter;
    private final CsvFormatter csvFormatter;
    //Constructor dependency here is used as its best compare to remaining two dependency injection method
    @Autowired
    public FileProcessorService(ParserProperties properties,
                                TextParser parser,
                                XmlFormatter xmlFormatter,
                                CsvFormatter csvFormatter) {
        this.properties = properties;
        this.parser = parser;
        this.xmlFormatter = xmlFormatter;
        this.csvFormatter = csvFormatter;
    }

    public void processFiles() throws IOException {
        Path inputFolder = Paths.get(properties.getInputFolder());
        Path outputFolder = Paths.get(properties.getOutputFolder());

        if (!Files.exists(outputFolder)) {
            Files.createDirectories(outputFolder);
            logger.info("Created output folder: {}", outputFolder);
        }

        // Below code is deleting i.e. clearing output folder..if any file exist
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(outputFolder)) {
            for (Path file : stream) {
                Files.delete(file);
                logger.debug("Deleted old file: {}", file.getFileName());
            }
        }

        // Below code is for processing all .in files
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(inputFolder, "*.in")) {
            for (Path inputFile : stream) {
                String inputText = Files.readString(inputFile);
                List<Sentence> sentences = parser.parse(inputText);
// using same name for output file eg: small.in will have output small.csv
                String baseName = inputFile.getFileName().toString().replaceFirst("\\.in$", "");
                Path xmlOutputFile = outputFolder.resolve(baseName + ".xml");
                Path csvOutputFile = outputFolder.resolve(baseName + ".csv");

                Files.writeString(xmlOutputFile, xmlFormatter.format(sentences));
                Files.writeString(csvOutputFile, csvFormatter.format(sentences));

                logger.info("Processed: {} → {}, {}", inputFile.getFileName(),
                        xmlOutputFile.getFileName(), csvOutputFile.getFileName());
            }
        }
    }
}
