package khai.edu.epos_re.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Silauras
 * Created on 16.06.2021 at 13:51
 */

@Controller
@RequestMapping("/error")
public class ErrorController {
    public String error(){
        return "error";
    }
}
