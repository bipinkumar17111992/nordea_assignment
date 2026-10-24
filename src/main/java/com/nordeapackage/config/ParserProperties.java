package com.nordeapackage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "parser")
public class ParserProperties {
    private String inputFolder;
    private String outputFolder;

    public String getInputFolder() {
        return inputFolder;
    }
    public void setInputFolder(String inputFolder) {
        this.inputFolder = inputFolder;
    }

    public String getOutputFolder() {
        return outputFolder;
    }
    public void setOutputFolder(String outputFolder) {
        this.outputFolder = outputFolder;
    }
}
