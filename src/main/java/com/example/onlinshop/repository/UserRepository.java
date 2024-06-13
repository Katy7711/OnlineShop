package com.example.onlinshop.repository;

import com.example.onlinshop.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);



}
