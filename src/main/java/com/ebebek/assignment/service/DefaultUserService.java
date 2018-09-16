package com.ebebek.assignment.service;

import com.ebebek.assignment.controller.UserCreationRequest;
import com.ebebek.assignment.model.User;
import com.ebebek.assignment.repository.UserRepository;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    private DaoAuthenticationProvider authenticationManager;

    @Autowired
    @Qualifier("dbBasedUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;

    @Override
    public User create(@Valid UserCreationRequest request) {
        User newUser = convertRequest(request);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(hashedPassword);
        newUser = userRepository.save(newUser);
        return newUser;
    }

    @Override
    public User currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return userRepository.findByUserName(currentUserName);
        }
        return null;
    }

    private static User convertRequest(UserCreationRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return user;
    }

}
