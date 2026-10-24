package com.nordeapackage.formatter;

import com.nordeapackage.model.Sentence;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvFormatterTest {

    private final CsvFormatter formatter = new CsvFormatter();

    @Test
    void testCsvOutput() {
        Sentence sentence = new Sentence(List.of("Hello", "World"));
        String csv = formatter.format(List.of(sentence));

        assertTrue(csv.contains("Sentence 1"));
        assertTrue(csv.contains("Hello"));
        assertTrue(csv.contains("World"));
    }
}
