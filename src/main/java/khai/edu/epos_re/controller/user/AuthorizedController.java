package khai.edu.epos_re.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Silauras
 * Created on 16.06.2021 at 15:49
 */

@Controller
public class AuthorizedController {

    @GetMapping("/user")
    public String user(){
        return "html/hello.html";
    }
}
