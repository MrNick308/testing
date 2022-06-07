package ru.dante.work.checking.yandexapi;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.dante.work.checking.checkresult.OrthographyCheckResult;
import ru.dante.work.checking.CheckService;
import ru.dante.work.checking.CheckText;
import ru.dante.work.checking.exceptions.ConnectionException;
import ru.dante.work.checking.exceptions.ParserException;
import ru.dante.work.checking.checkresult.mistake.Mistake;

import java.util.List;

@Service
public class YandexCheckService implements CheckService {

    @Getter
    private static YandexCheckService instance;

    public YandexCheckService() {
        instance = this;
    }

    /**
     * Проверка текста на орфографические ошибки сервисом Яндекс Спеллер.
     *
     * @param check текст, который необходимо проверить
     * @return результат проверки с ошибками и исправленным текстом
     */
    public void checkTextOrthography(CheckText check) {
        try {
            String rightText = check.getOldText();

            List<Mistake> foundedMistakes = YandexApi.getMistakes(check.getOldText());
            for (int i = foundedMistakes.size()-1; i >= 0; i--) {
                Mistake mistake = foundedMistakes.get(i);

                String oldWord = mistake.getOldWord();
                String rightWord = mistake.getRightWord();
                int pos = mistake.getPos();

                rightText = rightText.substring(0, pos) + rightWord + rightText.substring(pos + oldWord.length());
            }

            check.setCheckResult(new OrthographyCheckResult(rightText, foundedMistakes));
        } catch (ConnectionException | ParserException exception) {
            check.setCheckResult(new OrthographyCheckResult());

            System.out.println("Текст не проверен, так как возникла ошибка:\n" +
                    exception.getMessage());
        }
    }

    /*

    public void checkTextVolume(CheckText check) { ... }

    public void checkTextWater(CheckText check) { ... }

    . . .

     */

}
