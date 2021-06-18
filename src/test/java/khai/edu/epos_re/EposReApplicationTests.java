package khai.edu.epos_re;

import khai.edu.epos_re.model.department.repo.DepartmentRepo;
import khai.edu.epos_re.model.permission.repo.PermissionRepo;
import khai.edu.epos_re.model.user_account.repo.UserAccountRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EposReApplicationTests {

    @Autowired
    UserAccountRepo userAccountRepository;
    @Autowired
    DepartmentRepo departmentRepository;
    @Autowired
    PermissionRepo permissionRepository;

    @Test
    void contextLoads() {
    }

}
