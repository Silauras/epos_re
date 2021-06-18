package khai.edu.epos_re.model.department.repo;

import khai.edu.epos_re.model.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartmentRepo extends JpaRepository<Department, UUID> {
}
