package khai.edu.epos_re.service;

import khai.edu.epos_re.model.UserAccount;
import khai.edu.epos_re.model.repository.UserAccountRepository;
import khai.edu.epos_re.payload.request.RegistrationRequest;
import khai.edu.epos_re.security.filters.SecurityConstants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@AllArgsConstructor
@Service
public class RegistrationService {

    private final UserAccountRepository userAccountRepository;

    private final EntityManager entityManager;

    @Transactional
    public void registerUserAccount(RegistrationRequest request){
        UserAccount userAccount = UserAccount.builder()
                .username(request.getUsername())
                .hashedPassword(UserAccount.getSHA_256_SecurePassword(request.getPassword(), SecurityConstants.PASSWORD_SALT))
                .fullName(request.getPassword())
                .build();
        userAccountRepository.save(userAccount);

    }
}
