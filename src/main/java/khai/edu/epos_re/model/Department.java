package khai.edu.epos_re.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "department")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {

    @Id
    @Column(name = "department_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @EqualsAndHashCode.Exclude
    private UUID id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "head_user_account_id", referencedColumnName = "user_account_id")
    private UserAccount headUserAccount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_user_account_id", referencedColumnName = "user_account_id")
    private UserAccount adminUserAccount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "senior_derartment_id", referencedColumnName = "department_id")
    private Department seniorDepartment;

    @OneToOne(mappedBy = "seniorDepartment")
    private Department juniorDepartment;

    @OneToMany(mappedBy = "department")
    private Set<Permission> permissions = new HashSet<>();


}
