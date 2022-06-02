package ru.dante.work.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.nodes.Document;
import ru.dante.work.checking.CheckResult;
import ru.dante.work.exceptions.ConnectionException;

public class CheckUtils {

    /**
     * Проверка текста на орфографические ошибки сервисом Яндекс Спеллер.
     *
     * @param text текст, который необходимо проверить
     * @return результат проверки с ошибками и исправленным текстом
     */
    public static CheckResult checkText(String text) throws ConnectionException {
        Document document = RequestUtils.generateCheckYandexApiRequest(text);

        try {
            Object obj = new JSONParser().parse(document.body().toString()
                    .replace("<body>", "")
                    .replace("</body>", ""));

            JSONArray wrongs = (JSONArray) obj;
            StringBuilder errors = new StringBuilder();
            String result = text;

            for (Object w : wrongs) {
                JSONObject wrong = (JSONObject) w;

                String word = (String) wrong.get("word");
                String right = (String) (((JSONArray) wrong.get("s")).get(0));

                errors.append("В слове `").append(word).append("` допущена ошибка. Правильно: ").append(right).append("\n");

                result = result.replace(word, right);
            }

            return new CheckResult(errors.toString(), result);
        } catch (ParseException e) {
            System.out.println("Ошибка парсинга полученной страницы, вероятно поменялась структура выводимых данных: ");
            e.printStackTrace();

            return new CheckResult();
        }
    }

}
