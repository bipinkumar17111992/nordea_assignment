package com.nordeapackage.formatter;

import com.nordeapackage.model.Sentence;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class XmlFormatter {
    public String format(List<Sentence> sentences) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
        sb.append("<text>\n");
        for (Sentence sentence : sentences) {
            sb.append("    <sentence>\n");
            for (String word : sentence.words()) {
                sb.append("        <word>").append(word).append("</word>\n");
            }
            sb.append("    </sentence>\n");
        }
        sb.append("</text>");
        return sb.toString();
    }
}
