package ru.dante.work.checking;

import org.json.simple.parser.ParseException;
import ru.dante.work.checking.exceptions.ConnectionException;

/**
 * Сервис для проверок
 */
public interface CheckService {

    void checkTextOrthography(CheckText checkText) throws ConnectionException, ParseException;

    /*

      void checkTextVolume(CheckText checkText);

      void checkTextWater(CheckText checkText);

     */

}
