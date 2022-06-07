package ru.dante.work.checking.checkresult;

import lombok.Getter;

public abstract class CheckResult {
    @Getter
    private boolean complete = false;           // Пройдена ли проверка

    protected void completeCheck() {
        this.complete = true;
    }

}
