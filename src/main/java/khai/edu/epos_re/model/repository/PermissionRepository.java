package khai.edu.epos_re.model.repository;

import khai.edu.epos_re.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
}
