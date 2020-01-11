package com.test.management.system.service.user;

import com.test.management.system.entity.Test;
import com.test.management.system.entity.user.User;
import com.test.management.system.entity.user.UserRegistrationDto;
import com.test.management.system.service.CrudService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService, CrudService<User, Long> {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);

}