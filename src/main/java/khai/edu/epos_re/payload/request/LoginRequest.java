package khai.edu.epos_re.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Silauras
 * Created on 18.06.2021 at 15:47
 */

@Getter
@Setter
@ToString
public class LoginRequest {
    @NotBlank
    @Email
    private String username;
    @NotBlank
    private String password;
}
