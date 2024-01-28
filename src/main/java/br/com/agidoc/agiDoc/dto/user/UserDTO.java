package br.com.agidoc.agiDoc.dto.user;

import br.com.agidoc.agiDoc.model.department.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int idUser;
    private String registration;
    private String name;
    private String user;
    private String password;
    private String role;
    private String position;
    private Department department;
}
