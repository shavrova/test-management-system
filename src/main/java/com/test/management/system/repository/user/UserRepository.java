package com.test.management.system.repository.user;

import com.test.management.system.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository <User, Long > {
    User findByEmail(String email);
}
