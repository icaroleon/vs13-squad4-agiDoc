package br.com.agidoc.agiDoc.model.user;

import java.util.UUID;

import br.com.agidoc.agiDoc.model.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "USERS")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    UUID uuid = UUID.randomUUID();

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USERS")
    @SequenceGenerator(name = "SEQ_USERS", sequenceName = "seq_users", allocationSize = 1)
    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "registration")
    private String registration =  uuid.toString().substring(0, 6);

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String user;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "permission")
    private Role role;

    @Column(name = "position")
    private String position;

    @Column(name = "department")
    private Integer department;

    @Column(name = "status")
    private Status status;

    @Column(name = "email")
    private String email;

}