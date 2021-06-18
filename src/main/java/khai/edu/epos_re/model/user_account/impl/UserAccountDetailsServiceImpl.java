package khai.edu.epos_re.model.user_account.impl;

import khai.edu.epos_re.model.user_account.UserAccount;
import khai.edu.epos_re.model.user_account.repo.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Silauras
 * Created on 18.06.2021 at 14:50
 */

@Service
public class UserAccountDetailsServiceImpl implements UserAccountDetailsService{

    @Autowired
    UserAccountRepo userAccountRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username : " + username));
        return UserAccountDetailsImpl.build(userAccount);
    }
}
