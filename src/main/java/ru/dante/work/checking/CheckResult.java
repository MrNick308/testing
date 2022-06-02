package ru.dante.work.checking;

public class CheckResult {
    // Допущенные ошибки
    private String errors;
    // Исправленный текст
    private String right;

    public CheckResult(String errors, String right) {
        this.errors = errors;
        this.right = right;
    }

    public CheckResult() {

    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
}
