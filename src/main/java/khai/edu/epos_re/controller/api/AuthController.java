package khai.edu.epos_re.controller.api;

import khai.edu.epos_re.model.repository.UserAccountRepository;
import khai.edu.epos_re.payload.request.RegistrationRequest;
import khai.edu.epos_re.payload.response.MessageResponse;
import khai.edu.epos_re.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final RegistrationService registrationService;
    private final UserAccountRepository userAccountRepository;

        @PostMapping("/registration")
    public MessageResponse registerUserAccount(RegistrationRequest request){
        if (userAccountRepository.findByUsername(request.getUsername()) != null){
            return new MessageResponse("User with this username already exists!");
        }
        registrationService.registerUserAccount(request);
        return new MessageResponse("User successfully registered!");
    }
}
