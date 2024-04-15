package com.sb3.repository;

import com.sb3.entities.RolesEntity;
import com.sb3.enums.UserRolesEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Long> {

    RolesEntity findByName(UserRolesEnum name);

}
