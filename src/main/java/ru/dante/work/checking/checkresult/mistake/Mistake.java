package ru.dante.work.checking.checkresult.mistake;

import lombok.Getter;

public class Mistake {
    @Getter
    private String oldWord;     // Изначальное слово
    @Getter
    private String rightWord;   // Исправленное слово
    @Getter
    private int pos;            // Место в тексте

    public Mistake(String oldWord, String rightWord, int pos) {
        this.oldWord = oldWord;
        this.rightWord = rightWord;
        this.pos = pos;
    }
}
