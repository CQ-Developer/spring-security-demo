package org.huhu.spring.security.demo.repository;

import org.huhu.spring.security.demo.entity.otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptJpaRepository extends JpaRepository<otp, String> {}
