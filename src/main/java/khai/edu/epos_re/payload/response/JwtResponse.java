package khai.edu.epos_re.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

/**
 * @author Silauras
 * Created on 18.06.2021 at 15:48
 */
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private UUID id;
    private String username;
    private String fullName;
    private List<String> permissions;

    public JwtResponse(String accessToken, UUID id, String username, String fullName, List<String> permissions){
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.permissions = permissions;
    }
}
