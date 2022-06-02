import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.dante.work.checking.CheckResult;
import ru.dante.work.exceptions.ConnectionException;
import ru.dante.work.utils.CheckUtils;

public class CheckTest {
    static CheckResult check;

    @BeforeAll
    static void clearCheck() {
        check = null;
    }

    @Test
    void checkRight() throws ConnectionException {
        check = CheckUtils.checkText("Привет, как дела?");
        checkSumExceptions(0);
    }

    @Test
    void checkOneException() throws ConnectionException {
        check = CheckUtils.checkText("Пивет, как дела?");
        checkSumExceptions(1);
    }

    @Test
    void checkFullException() throws ConnectionException {
        check = CheckUtils.checkText("Пивет, кок дила?");
        checkSumExceptions(3);
    }

    void checkSumExceptions(int errors) {
        Assertions.assertEquals(check.getErrors().split("допущена ошибка. Правильно:").length, errors + 1);
    }

    @AfterAll
    static void compareWithRight() {
        Assertions.assertEquals(check.getRight(), "Привет, как дела?");
    }
}
