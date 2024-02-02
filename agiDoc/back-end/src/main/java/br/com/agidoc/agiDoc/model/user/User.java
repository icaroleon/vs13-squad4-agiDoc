package br.com.agidoc.agiDoc.model.user;
import java.util.Set;
import java.util.UUID;
import br.com.agidoc.agiDoc.model.department.Department;
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

    @Column(name = "user")
    private String user;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role = "admin";

    @Column(name = "position")
    private String position;

    @Column(name = "id_department")
    private Integer idDepartment;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_department", insertable = false, updatable = false)
//    private Department department;

}
