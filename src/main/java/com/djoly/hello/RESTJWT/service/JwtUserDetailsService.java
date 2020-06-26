package com.djoly.hello.RESTJWT.service;

import com.djoly.hello.RESTJWT.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Authorize only usertest
        if("usertest".equals(username)) {
            List<com.djoly.hello.RESTJWT.model.User> user =  userRepository.findUserByUsername(username);
            return new User(user.get(0).getUsername(),
                    "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
            //return userRepository.findUserByUsername(username);
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
