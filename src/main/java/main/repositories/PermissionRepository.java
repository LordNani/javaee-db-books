package main.repositories;

import main.domain.entities.BookEntity;
import main.domain.entities.PermissionEntity;
import main.domain.entities.UserEntity;
import main.domain.type.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Integer> {
    List<PermissionEntity> findByPermission(Permission permission);


}
