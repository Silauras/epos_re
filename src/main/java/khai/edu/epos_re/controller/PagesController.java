package khai.edu.epos_re.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/")
    public String mainPage(){
        return "static/html/index.html";
    }


}
