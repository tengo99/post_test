package com.testpost.springbootpost.repository;

import com.testpost.springbootpost.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
