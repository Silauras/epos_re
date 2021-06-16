package khai.edu.epos_re.controller.user;

import khai.edu.epos_re.entity.UserAccount;
import khai.edu.epos_re.repository.UserAccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    final
    UserAccountRepository userAccountRepository;

    public RegistrationController(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @GetMapping
    public String registation() {
        return "html/registration.html";
    }

    @PostMapping
    public String addUser(UserAccount user){
        UserAccount userAccountFromDb = userAccountRepository.findByUsername(user.getUsername());
        System.out.println(user);
        if (userAccountFromDb != null){
            return "redirect:html/registration.html";
        }
        user.setActive(true);
        System.out.println("Saved: " + user);
        userAccountRepository.save(user);
        return "redirect:html/login.html";
    }
}
