package com.nordeapackage.formatter;

import com.nordeapackage.model.Sentence;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class XmlFormatterTest {

    private final XmlFormatter formatter = new XmlFormatter();

    @Test
    void testXmlOutput() {
        Sentence sentence = new Sentence(List.of("Hello", "World"));
        String xml = formatter.format(List.of(sentence));

        assertTrue(xml.contains("<sentence>"));
        assertTrue(xml.contains("<word>Hello</word>"));
        assertTrue(xml.contains("<word>World</word>"));
    }
}
