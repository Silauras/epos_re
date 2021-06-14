package khai.edu.epos_re;

import khai.edu.epos_re.entity.Department;
import khai.edu.epos_re.entity.Permission;
import khai.edu.epos_re.entity.UserAccount;
import khai.edu.epos_re.repository.DepartmentRepository;
import khai.edu.epos_re.repository.PermissionRepository;
import khai.edu.epos_re.repository.UserAccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class EposReApplicationTests {

    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    PermissionRepository permissionRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void checkUserAccount() {
        UserAccount testUserAccount = UserAccount.builder()
                .username("testUserName")
                .displayedName("testName")
                .password("testPassword")
                .build();
        userAccountRepository.save(testUserAccount);
        if (testUserAccount != userAccountRepository.getById(testUserAccount.getId()))
            fail();
    }

    @Test
    void bigCheck(){
        UserAccount testUserAccountLeader = UserAccount.builder()
                .id(UUID.randomUUID())
                .username("testUserNameLeader")
                .displayedName("testNameLeader")
                .password("testPasswordLeader")
                .permissions(new HashSet<>())
                .build();
        UserAccount testUserAccountAdmin = UserAccount.builder()
                .id(UUID.randomUUID())
                .username("testUserNameAdmin")
                .displayedName("testNameAdmin")
                .password("testPasswordAdmin")
                .permissions(new HashSet<>())
                .build();

        userAccountRepository.saveAndFlush(testUserAccountLeader);
        userAccountRepository.saveAndFlush(testUserAccountAdmin);
        Department testDepartment = Department.builder()
                .id(UUID.randomUUID())
                .headUserAccount(testUserAccountLeader)
                .adminUserAccount(testUserAccountAdmin)
                .permissions(new HashSet<>())
                .build();
        Permission testPermission = Permission.builder().id(UUID.randomUUID()).department(testDepartment).build();
        testDepartment.getPermissions().add(testPermission);
        departmentRepository.saveAndFlush(testDepartment);
        testPermission.getUserAccounts().add(testUserAccountAdmin);
        permissionRepository.saveAndFlush(testPermission);

    }

}
