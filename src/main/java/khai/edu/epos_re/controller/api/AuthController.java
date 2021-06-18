package khai.edu.epos_re.controller.api;

import khai.edu.epos_re.model.user_account.UserAccount;
import khai.edu.epos_re.model.user_account.impl.UserAccountDetailsImpl;
import khai.edu.epos_re.model.user_account.repo.UserAccountRepo;
import khai.edu.epos_re.payload.request.LoginRequest;
import khai.edu.epos_re.payload.request.SignupRequest;
import khai.edu.epos_re.payload.response.JwtResponse;
import khai.edu.epos_re.payload.response.MessageResponse;
import khai.edu.epos_re.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Silauras
 * Created on 18.06.2021 at 15:29
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserAccountRepo userAccountRepo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        System.err.println(loginRequest.toString());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserAccountDetailsImpl userAccountDetails = (UserAccountDetailsImpl) authentication.getPrincipal();
        List<String> permissions = userAccountDetails.getPermissions().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userAccountDetails.getId(),
                userAccountDetails.getUsername(),
                userAccountDetails.getFullname(),
                permissions
                ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userAccountRepo.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in user!"));
        }

        // Create new user's account
        UserAccount userAccount = UserAccount.builder()
                .username(signupRequest.getUsername())
                .fullName(signupRequest.getFullName())
                .password(encoder.encode(signupRequest.getPassword()))
                .build();

        userAccountRepo.save(userAccount);

        return ResponseEntity.ok(new MessageResponse("User registered succesfully!"));
    }
}
