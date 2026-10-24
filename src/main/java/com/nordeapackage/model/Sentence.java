package com.nordeapackage.model;

import java.util.List;
import java.util.Objects;

public record Sentence(List<String> words) {
    public Sentence(List<String> words) {
        this.words = List.copyOf(words);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sentence sentence)) return false;
        return Objects.equals(words, sentence.words);
    }

}
