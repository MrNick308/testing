package ru.dante.work.checking.yandexapi;

import lombok.Getter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.dante.work.checking.exceptions.ConnectionException;

import java.io.IOException;

import static sun.net.www.protocol.http.HttpURLConnection.userAgent;

public class YandexApiRequestGenerator {

    @Getter
    private final static String BASE_URL = "https://speller.yandex.net/services/spellservice.json";

    public static Document generateCheckByYandexApi(String text) throws ConnectionException {
        Connection connection = Jsoup.connect(BASE_URL + "/checkText"/*?text=" + text*/)
                .data("text", text)
                .method(Connection.Method.POST)
                .userAgent(userAgent)
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .timeout(30000);

        try {
            return connection.get();
        } catch (IOException e) {
            throw new ConnectionException("Yandex Service close connection!");
        }
    }

    /*

    public static Document getImageByYandexApi(String text) { return image; }

    public static Document getRandomTextByYandexApi() { return randomText; }

    . . .

     */
}
