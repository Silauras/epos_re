package khai.edu.epos_re.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegistrationRequest {
    String username;
    String fullName;
    String password;
}
