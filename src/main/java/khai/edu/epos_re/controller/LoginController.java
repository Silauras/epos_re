package khai.edu.epos_re.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class LoginController {

    @GetMapping("/login/{lang}")
    public String login(@PathVariable String lang) {
        switch (lang) {
            case "en":
                return "html/login_en.html";
            case "ua":
                return "html/login_ua.html";
            default:
                return "html/login_en.html";

        }
    }
}
