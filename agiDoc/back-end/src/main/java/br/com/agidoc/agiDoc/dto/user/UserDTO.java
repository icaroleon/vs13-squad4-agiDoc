package br.com.agidoc.agiDoc.dto.user;

import br.com.agidoc.agiDoc.model.Status;
import br.com.agidoc.agiDoc.model.department.Department;
import br.com.agidoc.agiDoc.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer idUser;
    private String registration;
    private String name;
    private String user;
    private String password;
    private Role role;
    private String position;
    private Integer department;
    private Status status;
    private String email;
}
