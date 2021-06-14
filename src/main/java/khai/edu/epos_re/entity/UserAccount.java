package khai.edu.epos_re.entity;


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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

    @Column(name = "displayed_name")
    private String displayedName;

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
