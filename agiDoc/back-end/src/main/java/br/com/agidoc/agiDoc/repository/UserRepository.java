package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.agidoc.agiDoc.model.user.User;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    User findUserByUser(String user);

    User findUserByPermissionType(Integer role);

    List<User> findUserByStatus(Status status);

    Page<User> findUsersByStatus(Status status, Pageable pageable);

    Optional<User> findUsersByUserAndPassword(String login, String password);

    Optional<User> findByUser(String username);
}
