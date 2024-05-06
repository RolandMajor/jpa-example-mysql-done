package user.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="my_user")
public class User {

    @Id
    private String username;

    @Column(nullable=false)
    private String password;

    @Version
    @Setter(AccessLevel.NONE)
    private long version;
}
