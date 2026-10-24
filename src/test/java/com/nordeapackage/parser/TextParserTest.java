package com.nordeapackage.parser;

import com.nordeapackage.model.Sentence;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextParserTest {

    private final TextParser parser = new TextParser();

    @Test
    void testParseSimpleSentence() {
        String input = "Hello world!";
        List<Sentence> sentences = parser.parse(input);

        assertEquals(1, sentences.size());
        assertTrue(sentences.get(0).words().contains("Hello"));
        assertTrue(sentences.get(0).words().contains("world"));

    }

    @Test
    void testMultipleSentences() {
        String input = "Java is great. Spring Boot is powerful!";
        List<Sentence> sentences = parser.parse(input);

        assertEquals(2, sentences.size());
    }
}
