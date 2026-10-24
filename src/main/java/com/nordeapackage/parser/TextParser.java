package com.nordeapackage.parser;

import com.nordeapackage.exception.TextParseException;
import com.nordeapackage.model.Sentence;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
@Component
public class TextParser {
// Here intention is to find if '.' is end of sentence or it used with any word eg: Mr.
    private static final Pattern SENTENCE_SPLIT = Pattern.compile(
            "(?<!Mr)(?<!Mrs)(?<!Ms)(?<!Dr)(?<!Prof)(?<!Sr)(?<!Jr)" +
                    "(?<!etc)(?<!e\\.g)(?<!i\\.e)(?<!vs)(?<!U\\.S\\.A)(?<!U\\.K)(?<!Ph\\.D)\\.|[!?]"
    );
    private static final Pattern WORD_SPLIT = Pattern.compile("[\\s,()\\-]+");

    public List<Sentence> parse(String input) {
        try {
            return SENTENCE_SPLIT.splitAsStream(input.trim())
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(this::parseSentence)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new TextParseException("Failed to parse text", e);
        }
    }

    private Sentence parseSentence(String sentenceText) {
        Comparator<String> lowercaseFirstComparator = (a, b) -> {
            int cmp = a.compareToIgnoreCase(b);
            if (cmp != 0) return cmp;
            // If equal ignoring case, lowercase comes first
            if (Character.isLowerCase(a.charAt(0)) && Character.isUpperCase(b.charAt(0))) return -1;
            if (Character.isUpperCase(a.charAt(0)) && Character.isLowerCase(b.charAt(0))) return 1;
            return a.compareTo(b);
        };

        List<String> words = WORD_SPLIT.splitAsStream(sentenceText.trim())
                .filter(w -> !w.isEmpty())
                .sorted(lowercaseFirstComparator)
                .collect(Collectors.toList());


        return new Sentence(words);
    }
}
