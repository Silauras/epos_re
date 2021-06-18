package khai.edu.epos_re.model.permission.repo;

import khai.edu.epos_re.model.permission.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepo extends JpaRepository<Permission, UUID> {
}
