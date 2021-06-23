package khai.edu.epos_re.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccount {

    @Id
    @Column(name = "user_account_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @EqualsAndHashCode.Exclude
    private UUID id;

    @Column
    private String username;

    @Column
    private String hashedPassword;

    @Column
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

    @SneakyThrows
    public static String getSHA_256_SecurePassword(String passwordToHash, String salt){
        String generatedPassword = null;

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = messageDigest.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
        StringBuilder stringBuilder = new StringBuilder();
        for (byte aByte : bytes) {
            stringBuilder.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));

        }
        generatedPassword = stringBuilder.toString();
        return generatedPassword;
    }

}