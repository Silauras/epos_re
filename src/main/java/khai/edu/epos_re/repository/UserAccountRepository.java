package khai.edu.epos_re.repository;

import khai.edu.epos_re.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserAccountRepository  extends JpaRepository<UserAccount, UUID> {
    UserAccount findByUsername(String username);
}
