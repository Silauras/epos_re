package khai.edu.epos_re.controller.user;


import khai.edu.epos_re.entity.UserAccount;
import khai.edu.epos_re.repository.UserAccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    final
    UserAccountRepository userAccountRepository;

    public LoginController(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @GetMapping
    public String login() {
        return "html/login.html";
    }


    @PostMapping
    public String auth(UserAccount user, Map<String, Object> model){
        UserAccount userAccountFromDb = userAccountRepository.findByUsername(user.getUsername());
        if (userAccountFromDb != null){
            model.put("user", userAccountFromDb);
        }
        return "redirect:html/index.html";
    }
}
