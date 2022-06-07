package ru.dante.work.checking.requests;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dante.work.checking.CheckText;
import ru.dante.work.checking.utils.ResponseUtils;

@Controller
@RequestMapping("/api/v1/")
public class CheckingController {

    @RequestMapping(value="/checking", method=RequestMethod.GET)
    public String checkingForm(Model model) {
        model.addAttribute("checktext", new CheckText());
        return "checking";
    }

    @RequestMapping(value="/checking", method=RequestMethod.POST)
    public String checkingSubmit(@ModelAttribute CheckText checking, Model model) {
        model.addAttribute("checktext", checking);
        return "result";
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object> checkText(
            @RequestParam(value = "text", required = false, defaultValue = "null") final String text
    ) {
        CheckText check = new CheckText(text);
        return ResponseUtils.generateOrthographyCheckAnswer(check);
    }
}
