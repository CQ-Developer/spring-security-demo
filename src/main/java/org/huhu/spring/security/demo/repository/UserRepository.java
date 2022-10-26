package org.huhu.spring.security.demo.repository;

import org.huhu.spring.security.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 根据用户名查找用户
     */
    Optional<User> findUserByUsername(String username);

}
