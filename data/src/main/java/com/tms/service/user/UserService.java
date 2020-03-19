package com.tms.service.user;

import com.tms.model.entity.user.User;
import com.tms.model.entity.user.UserRegistrationDto;
import com.tms.service.CrudService;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService, CrudService<User, Long> {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);

}