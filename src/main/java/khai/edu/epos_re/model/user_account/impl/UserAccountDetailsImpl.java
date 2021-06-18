package khai.edu.epos_re.model.user_account.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import khai.edu.epos_re.model.user_account.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Silauras
 * Created on 18.06.2021 at 13:05
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserAccountDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private UUID id;

    private String username;

    private String fullname;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> permissions;

    @Override
    public String getUsername() {
        return getUsername();
    }

    @Override
    public String getPassword() {
        return getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions;
    }

    public static UserAccountDetailsImpl build(UserAccount user) {
        List<GrantedAuthority> authorities = user.getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toList());

        return UserAccountDetailsImpl.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullname(user.getFullName())
                .password(user.getPassword())
                .permissions(authorities)
                .build();
    }
}
