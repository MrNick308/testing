package ru.dante.work.requests;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dante.work.checking.Check;

@Controller
@RequestMapping("/api/v1/")
public class CheckingController {

    @RequestMapping(value="/checking", method=RequestMethod.GET)
    public String checkingForm(Model model) {
        model.addAttribute("checking", new Check());
        return "checking";
    }

    @RequestMapping(value="/checking", method=RequestMethod.POST)
    public String checkingSubmit(@ModelAttribute Check checking, Model model) {
        model.addAttribute("checking", checking);
        return "result";
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> checkText(
            @RequestParam(value = "text", required = false, defaultValue = "null") final String text
    ) {
        HttpHeaders headers = new HttpHeaders();

        Check check = new Check();
        check.setText(text);

        if(check.getCheckResult().getRight() == null) {
            return new ResponseEntity<>("Проверка не выполнена!", headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        StringBuilder result = new StringBuilder();
        result.append("Исходный текст: ").append(check.getText()).append("\n");
        if(!check.getCheckResult().getErrors().equals("")) {
            result.append(check.getCheckResult().getErrors())
                    .append("Исправленный текст: ").append(check.getCheckResult().getRight());
        } else {
            result.append("\n").append("Ошибки не найдены! :)");
        }

        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
}
