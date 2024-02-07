package br.com.agidoc.agiDoc.model.user;

import java.util.Collection;
import java.util.UUID;

import br.com.agidoc.agiDoc.model.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "USERS")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "USERS")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USERS")
    @SequenceGenerator(name = "SEQ_USERS", sequenceName = "seq_users", allocationSize = 1)
    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "registration")
    private String registration = UUID.randomUUID().toString().substring(0, 6);

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String user;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "permission")
    private Permission permission;

    @Column(name = "position")
    private String position;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "department")
    private Department department;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private Status status;

    @Column(name = "email")
    private String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}