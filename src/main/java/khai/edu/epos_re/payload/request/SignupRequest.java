package khai.edu.epos_re.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Silauras
 * Created on 18.06.2021 at 15:43
 */

@Getter
@Setter
public class SignupRequest {
    @NotBlank
    @Size(min = 1, max = 60)
    private String fullName;

    @NotBlank
    @Size
    @Email
    private String username;

    @NotBlank
    @Size(min = 8, max = 32)
    private String password;
}
