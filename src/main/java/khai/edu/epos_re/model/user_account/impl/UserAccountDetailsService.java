package khai.edu.epos_re.model.user_account.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Silauras
 * Created on 18.06.2021 at 13:03
 */
public interface UserAccountDetailsService extends UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
