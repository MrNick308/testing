package ru.dante.work.checking.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.dante.work.checking.CheckText;
import ru.dante.work.checking.checkresult.OrthographyCheckResult;
import ru.dante.work.checking.checkresult.mistake.Mistake;

public class ResponseUtils {

    /**
     * Сформировать ответ о проверке орфографии текста
     * @param check проверяемый текст
     * @return сформированный ответ
     */
    public static ResponseEntity<Object> generateOrthographyCheckAnswer(CheckText check) {
        if(check.getCheckResult() == null || !check.getCheckResult().isComplete()) {
            return createAnswer("Проверка не выполнена!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        OrthographyCheckResult checkResult = (OrthographyCheckResult) check.getCheckResult();

        StringBuilder result = new StringBuilder();
        result.append("Исходный текст: ").append(check.getOldText()).append("\n");

        for(Mistake mistake : checkResult.getMistakes()) {
            result.append("Допущена ошибка: `").append(mistake.getOldWord()).append("` в слове `")
                    .append(mistake.getRightWord()).append("`\n");
        }

        if(!checkResult.getMistakes().isEmpty()) {
            result.append("Исправленный текст: ").append(checkResult.getRightText());
        } else {
            result.append("Ошибки не найдены! :)");
        }

        return createAnswer(result, HttpStatus.OK);
    }

    private static ResponseEntity<Object> createAnswer(Object object, HttpStatus httpStatus) {
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(object, headers, httpStatus);
    }

    /*

    public static ResponseEntity<Object> generateVolumeCheckAnswer(CheckText check) { ... }

    public static ResponseEntity<Object> generateWaterCheckAnswer(CheckText check) { ... }

    . . .

     */

}
