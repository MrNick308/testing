package ru.dante.work.checking;

import lombok.Getter;
import lombok.Setter;
import ru.dante.work.checking.checkresult.CheckResult;
import ru.dante.work.checking.yandexapi.YandexCheckService;

/**
 * Проверка текста
 */
public class CheckText {

    // Исходный текст
    private String oldText;
    // Результат проверки
    @Getter @Setter
    private CheckResult checkResult;

    public CheckText() { }

    public CheckText(String text) {
        this.setOldText(text);
    }

    public void setOldText(String oldText) {
        this.oldText = oldText;
        this.startCheck();
    }

    public String getOldText() {
        return oldText;
    }

    private void startCheck() {
        YandexCheckService.getInstance().checkTextOrthography(this);
    }
}
