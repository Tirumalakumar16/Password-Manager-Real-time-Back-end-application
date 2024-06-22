package com.passwordmanager.passwords.service.security;

import com.passwordmanager.passwords.config.MyUsers;
import com.passwordmanager.passwords.models.Users;
import com.passwordmanager.passwords.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoDetails implements UserDetailsService {
        @Autowired
        private UsersRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user =userRepository.findByUserName(username);
        user.orElseThrow(() ->new UsernameNotFoundException(String.format("%s not exist",username)));
        return user.map(MyUsers::new).get();
    }
}
