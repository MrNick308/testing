package ru.dante.work.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.dante.work.exceptions.ConnectionException;

import java.io.IOException;

import static sun.net.www.protocol.http.HttpURLConnection.userAgent;

public class RequestUtils {

    private static final String BASE_URL = "https://speller.yandex.net/services/spellservice.json";

    public static Document generateCheckYandexApiRequest(String text) throws ConnectionException {
        Connection connection = Jsoup.connect(BASE_URL + "/checkText?text=" + text)
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
}
