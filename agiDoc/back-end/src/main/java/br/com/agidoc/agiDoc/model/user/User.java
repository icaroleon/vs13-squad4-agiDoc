package br.com.agidoc.agiDoc.model.user;
import java.util.UUID;
import br.com.agidoc.agiDoc.model.department.Department;

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

    public User(){}

    @Override
    public String toString() {
        return " Users {" +
                "\n\tuser_id: " + idUser +
                "\n\tRegistration: '" + registration + '\'' +
                ",\n\tName: '" + name + '\'' +
                ",\n\tUser: '" + user + '\'' +
                ",\n\tRole: '" + role + '\'' +
                ",\n\tposition: '" + position + '\'' +
                ",\n\tCompany: " + (department != null ? department.getName() : "null") +
                "\n}";
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
