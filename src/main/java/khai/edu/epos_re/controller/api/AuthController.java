package khai.edu.epos_re.controller.api;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import khai.edu.epos_re.model.UserAccount;
import khai.edu.epos_re.model.repository.UserAccountRepository;
import khai.edu.epos_re.payload.request.LoginRequest;
import khai.edu.epos_re.payload.request.RegistrationRequest;
import khai.edu.epos_re.payload.response.MessageResponse;
import khai.edu.epos_re.security.filters.JwtAuthenticationFilter;
import khai.edu.epos_re.security.filters.JwtAuthorizationFilter;
import khai.edu.epos_re.security.filters.SecurityConstants;
import khai.edu.epos_re.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final RegistrationService registrationService;
    private final UserAccountRepository userAccountRepository;
    /*@RequestMapping("/login")
    public ResponseEntity<?> loginUserAccount() {
        return ResponseEntity.ok().body("ok");

    }*/

    @PostMapping("/registration")
    public ResponseEntity<?> registerUserAccount(@RequestBody RegistrationRequest request) {
        if (userAccountRepository.findByUsername(request.getUsername()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("User with this username already exists!"));
        }
        log.error("Trying to register user" + request.toString());
        registrationService.registerUserAccount(request);
        return ResponseEntity
                .ok()
                .body(new MessageResponse("User successfully registered!"));
    }
}
