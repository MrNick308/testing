package ru.dante.work.checking.checkresult;

import lombok.Getter;
import lombok.Setter;
import ru.dante.work.checking.checkresult.mistake.Mistake;

import java.util.ArrayList;
import java.util.List;

public class OrthographyCheckResult extends CheckResult {

    @Getter
    private List<Mistake> mistakes;             // Допущенные ошибки
    @Setter @Getter
    private String rightText;                   // Исправленный текст

    public OrthographyCheckResult() {
        this.mistakes = new ArrayList<>();
    }

    public OrthographyCheckResult(String rightText, List<Mistake> foundedMistakes) {
        this.rightText = rightText;
        this.mistakes = foundedMistakes;

        this.completeCheck();
    }

    public void addMistake(Mistake mistake) {
        mistakes.add(mistake);
    }

}
