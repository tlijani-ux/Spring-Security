package com.seif.SpringSecurity.repository;

import com.seif.SpringSecurity.entities.Role;
import com.seif.SpringSecurity.entities.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     Optional<User> findByEmail(String email);
     User  findByRole(Role role);
}
