package org.huhu.spring.security.demo.repository;

import org.huhu.spring.security.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, String> {}
