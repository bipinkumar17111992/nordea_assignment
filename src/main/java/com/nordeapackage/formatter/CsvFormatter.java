package com.nordeapackage.formatter;


import com.nordeapackage.model.Sentence;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CsvFormatter {
    public String format(List<Sentence> sentences) {
        StringBuilder sb = new StringBuilder();
        int maxWords = sentences.stream().mapToInt(s -> s.words().size()).max().orElse(0);

        // Header
        sb.append(", ");
        for (int i = 1; i <= maxWords; i++) {
            sb.append("Word ").append(i);
            if (i < maxWords) sb.append(", ");
        }
        sb.append("\n");

        // Rows
        int index = 1;
        for (Sentence sentence : sentences) {
            sb.append("Sentence ").append(index++).append(", ");
            sb.append(String.join(", ", sentence.words()));
            sb.append("\n");
        }
        return sb.toString();
    }
}
