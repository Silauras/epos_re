package khai.edu.epos_re.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "permission")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Permission implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @EqualsAndHashCode.Exclude
    @Column(name = "permission_id")
    private UUID id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(mappedBy = "permissions")
    private Set<UserAccount> userAccounts = new HashSet<>();

    @Override
    public String getAuthority() {
        return name;
    }
}
