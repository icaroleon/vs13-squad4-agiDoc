package controller;

import exception.DatabaseException;
import model.user.User;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    private static final UserService userService = new UserService();

    public static boolean login(String userName, String password) throws DatabaseException {
        try {
           if (userService.list().stream().anyMatch(user -> user.getUser().equals(userName) && user.getPassword().equals(password))){
               return true;
           } else {
               System.out.println("User doesn't exists.");
               return false;
           }
        } catch (DatabaseException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public static void addUser(User user) {
        try {
            User addedUser = userService.create(user);
            System.out.println("User add successfully! " + addedUser);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public static void removeUser(Integer id) {
        try {
            boolean removedUser = userService.delete(id);
            System.out.println("Removed? " + removedUser + "| com id= " + id);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public static void editUser(Integer id, User user) {
        try {
            boolean editedUser = userService.update(id, user);
            System.out.println("Edited? " + editedUser + "| com id= " + id);
        } catch (DatabaseException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void getUsers() {
        try {
            userService.list().forEach(System.out::println);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public static void getUserById(Integer id) {
        try {
            userService.ListUser(id).forEach(System.out::println);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
