package ru.dante.work.checking;

import ru.dante.work.exceptions.ConnectionException;

import static ru.dante.work.utils.CheckUtils.checkText;

public class Check {

    // Исходный текст
    private String text;
    // Результат проверки
    private CheckResult checkResult;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;

        try {
            checkResult = checkText(text);
        } catch (ConnectionException e) {
            checkResult = new CheckResult();
        }
    }

    public CheckResult getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(CheckResult checkResult) {
        this.checkResult = checkResult;
    }
}
