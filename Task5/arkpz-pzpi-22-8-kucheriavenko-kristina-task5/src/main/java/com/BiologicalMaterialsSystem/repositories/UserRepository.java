package com.BiologicalMaterialsSystem.src.main.java.com.BiologicalMaterialsSystem.repositories;

import com.BiologicalMaterialsSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

}
