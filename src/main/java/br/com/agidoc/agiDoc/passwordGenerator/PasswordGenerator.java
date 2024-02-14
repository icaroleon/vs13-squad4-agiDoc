package br.com.agidoc.agiDoc.passwordGenerator;

import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        StandardPasswordEncoder standardPasswordEncoder = new StandardPasswordEncoder();
        String password = standardPasswordEncoder.encode("123");
        System.out.println(password);
        // $2a$10$fP3fNbhDrkixHZHOqW4zKu9QdYiIWkhxH8NIXWcq7AQiUXAHivZEO
        // $2a$10$LMNeJo9gpeZ2mMO5BO2fGem8LWlYGIZuqsFEmg4uT09NbCly6ojq2
        // $2a$10$PjfEN2AgepvCUJe7qVnxEedrLKZV0OoIvx4X20YiiG4kxV9CAHxt2

        String encryptedPassword = "d097dc337a5a72b5b3053cf8de36331240390076c5811ae71c8e37291346d75920ac8713a36ad99b";
        boolean matches = standardPasswordEncoder.matches("123", encryptedPassword);
        System.out.println(matches);
        //true ou false
    }
}
