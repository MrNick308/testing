import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.dante.work.checking.CheckTestConfig;
import ru.dante.work.checking.CheckText;
import ru.dante.work.checking.checkresult.OrthographyCheckResult;
import ru.dante.work.checking.yandexapi.YandexCheckService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=CheckTestConfig.class)
public class CheckTest {

    @Autowired
    private static YandexCheckService yandexCheckService;

    private static CheckText check;

    @BeforeAll
    static void clearCheck() {
        check = null;
    }

    @Test
    void checkRight() {
        check = new CheckText("Привет, как дела?");
        checkSumExceptions(0);
    }

    @Test
    void checkOneException() {
        check = new CheckText("Пивет, как дела?");
        checkSumExceptions(1);
    }

    @Test
    void checkFullException() {
        check = new CheckText("Пивет, кок дила?");
        checkSumExceptions(3);
    }

    void checkSumExceptions(int errors) {
        Assertions.assertEquals(errors, ((OrthographyCheckResult) check.getCheckResult()).getMistakes().size());
    }

    @AfterAll
    static void compareWithRight() {
        Assertions.assertEquals(((OrthographyCheckResult) check.getCheckResult()).getRightText(), "Привет, как дела?");
    }
}
