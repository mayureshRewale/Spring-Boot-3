package com.sb3.repository;

import com.sb3.entities.UserDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Long> {

    UserDetailsEntity findByEmail(String email);

}
