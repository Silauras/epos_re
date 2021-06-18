package khai.edu.epos_re.model.department;

import khai.edu.epos_re.model.permission.Permission;
import khai.edu.epos_re.model.user_account.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "department_id")
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
