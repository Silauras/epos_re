package khai.edu.epos_re.model.user_account;


import khai.edu.epos_re.model.department.Department;
import khai.edu.epos_re.model.permission.Permission;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_account")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccount {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_account_id")
    @EqualsAndHashCode.Exclude
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullName;



    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_account_permission",
            joinColumns = {@JoinColumn(name = "user_account_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")}
    )
    private Set<Permission> permissions = new HashSet<>();

    @OneToOne(mappedBy = "headUserAccount")
    private Department departmentWhereHead;

    @OneToOne(mappedBy = "adminUserAccount")
    private Department departmentWhereAdmin;

}
