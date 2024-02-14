package br.com.agidoc.agiDoc.repository;

import br.com.agidoc.agiDoc.model.permission.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    Optional<Permission> getPermissionByName(String permissionName);
}