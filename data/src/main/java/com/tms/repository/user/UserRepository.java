package com.tms.repository.user;

import com.tms.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository <User, Long > {
    User findByEmail(String email);
}
