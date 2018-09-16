package com.ebebek.assignment.security;

import com.ebebek.assignment.model.User;
import com.ebebek.assignment.repository.UserRepository;
import java.util.Collections;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier("dbBasedUserDetailsService")
public class DbBasedUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Set<SimpleGrantedAuthority> roles = Collections.singleton(new SimpleGrantedAuthority("user"));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(),
                true, true, true, true, roles);

        return userDetails;
    }



}