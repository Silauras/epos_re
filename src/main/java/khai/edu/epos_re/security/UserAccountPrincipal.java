package khai.edu.epos_re.security;

import khai.edu.epos_re.model.Permission;
import khai.edu.epos_re.model.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class UserAccountPrincipal implements UserDetails {

    private final UserAccount userAccount;

    public UserAccountPrincipal(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public UUID getUserId() {
        return userAccount.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Permission permission :
                userAccount.getPermissions()) {
            authorities.add(new SimpleGrantedAuthority(permission.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return userAccount.getHashedPassword();
    }

    @Override
    public String getUsername() {
        return userAccount.getUsername();
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
}
