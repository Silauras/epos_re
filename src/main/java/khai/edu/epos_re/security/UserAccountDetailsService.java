package khai.edu.epos_re.security;

import khai.edu.epos_re.model.UserAccount;
import khai.edu.epos_re.model.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAccountDetailsService implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        if (userAccount == null){
            throw new UsernameNotFoundException(username);
        }
        return new UserAccountPrincipal(userAccount);
    }
}
