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
@Table(name = "USERS")
public class User {
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
}