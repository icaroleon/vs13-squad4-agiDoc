package br.com.agidoc.agiDoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.agidoc.agiDoc.model.user.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    User findUserByUser(String user);

}
