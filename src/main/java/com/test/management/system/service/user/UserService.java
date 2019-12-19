package com.test.management.system.service.user;

import com.test.management.system.entity.user.User;
import com.test.management.system.entity.user.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}