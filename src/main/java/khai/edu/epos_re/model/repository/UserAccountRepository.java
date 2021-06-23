package khai.edu.epos_re.model.repository;

import khai.edu.epos_re.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {
    public UserAccount findByUsername(String username);
}
