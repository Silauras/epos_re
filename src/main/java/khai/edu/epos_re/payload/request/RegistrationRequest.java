package khai.edu.epos_re.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestBody;


@Data
@AllArgsConstructor
public class RegistrationRequest {
    String username;
    String fullName;
    String password;
}
