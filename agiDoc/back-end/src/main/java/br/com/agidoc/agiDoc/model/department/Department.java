package br.com.agidoc.agiDoc.model.department;

import br.com.agidoc.agiDoc.model.user.User;

import java.util.ArrayList;

public class Department {
    private int idDepartment = 1;
    private String name;
    private User user;

    private ArrayList<User> users;

    public int getIdDepartment() {
        return idDepartment;
    }

    public Department() {
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
