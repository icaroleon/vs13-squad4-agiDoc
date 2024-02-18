package br.com.agidoc.agiDoc.dto.user;

import br.com.agidoc.agiDoc.model.Status;
import br.com.agidoc.agiDoc.model.permission.Permission;
import br.com.agidoc.agiDoc.model.user.Department;
import br.com.agidoc.agiDoc.model.user.PermissionType;
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
    private Permission permission;
    private String position;
    private Department department;
    private Status status;
    private String email;
}
