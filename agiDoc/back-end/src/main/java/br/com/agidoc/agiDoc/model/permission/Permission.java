package br.com.agidoc.agiDoc.model.permission;

import br.com.agidoc.agiDoc.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "PERMISSION")
public class Permission implements GrantedAuthority {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_PERMISSION")
    private int idPermission;

    @Column(name = "NAME")
    private String name;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "USER_PERMISSION",
            joinColumns = @JoinColumn(name = "ID_PERMISSION"),
            inverseJoinColumns = @JoinColumn(name = "ID_USER")
    )
    private Set<User> users;

    @Override
    public String getAuthority() {
        return name;
    }
}
