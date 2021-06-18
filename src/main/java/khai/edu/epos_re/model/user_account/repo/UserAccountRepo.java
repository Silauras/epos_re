package khai.edu.epos_re.model.user_account.repo;

import khai.edu.epos_re.model.user_account.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserAccountRepo extends JpaRepository<UserAccount, UUID> {
    Optional<UserAccount> findByUsername(String username);


    boolean existsByUsername(String email);
}
