package br.com.agidoc.agiDoc.model.user;
import java.util.UUID;
import br.com.agidoc.agiDoc.model.department.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    UUID uuid = UUID.randomUUID();

    private int idUser;
    private String registration =  uuid.toString().substring(0, 6);
    private String name;
    private String user;
    private String password;
    private String role = "admin";
    private String position;
    private Department department = new Department();
}
