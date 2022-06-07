package ru.dante.work.checking.yandexapi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.nodes.Document;
import ru.dante.work.checking.exceptions.ConnectionException;
import ru.dante.work.checking.exceptions.ParserException;
import ru.dante.work.checking.checkresult.mistake.Mistake;

import java.util.ArrayList;
import java.util.List;

/**
 * Этот класс реализует возможности Yandex API
 */
public class YandexApi {

    /**
     * Получить список ошибок в тексте
     * @param text проверяемый текст
     * @return список ошибок
     * @throws ConnectionException ошибка соединения с яндексом
     * @throws ParserException ошибка разбора объекта от яндекса
     */
    public static List<Mistake> getMistakes(String text) throws ConnectionException, ParserException {
        List<Mistake> mistakes = new ArrayList<>();

        JSONArray foundedMistakes = YandexApi.getMistakesInJSON(text);
        for (Object mistake : foundedMistakes) {
            JSONObject wrong = (JSONObject) mistake;

            if(((JSONArray) wrong.get("s")).get(0) != null) {
                String word = (String) wrong.get("word");
                String right = (String) (((JSONArray) wrong.get("s")).get(0));
                int pos = ((Long) wrong.get("pos")).intValue();

                mistakes.add(new Mistake(word, right, pos));
            }
        }

        return mistakes;
    }

    /**
     * Получить JSON массив ошибок в тексте
     * @param text проверяемый текст
     * @return список ошибок
     * @throws ConnectionException ошибка соединения с яндексом
     * @throws ParserException ошибка разбора объекта от яндекса
     */
    public static JSONArray getMistakesInJSON(String text) throws ConnectionException, ParserException {
        Document document = YandexApiRequestGenerator.generateCheckByYandexApi(text);

        try {
            Object obj = new JSONParser().parse(document.body().toString()
                    .replace("<body>", "")
                    .replace("</body>", ""));

            return (JSONArray) obj;
        } catch (ParseException e) {
            throw new ParserException(document);
        }
    }

    /*

    public static Object getImage() { return image; }

    public static Object getRandomText() { return randomText; }

    . . .

     */
}
