package com.passwordmanager.passwords.controllers;

import com.passwordmanager.passwords.dtos.AuthRequest;
import com.passwordmanager.passwords.dtos.CreateUserRequestDto;
import com.passwordmanager.passwords.dtos.ResposneUserDto;
import com.passwordmanager.passwords.exceptions.UserNameNotLoaded;
import com.passwordmanager.passwords.service.UsersService;
import com.passwordmanager.passwords.service.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/password.manager.com")
public class UserController {


        private UsersService usersService;

        private AuthenticationManager authenticationManager;

        private JwtService jwtService;

    public UserController(UsersService usersService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.usersService = usersService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/user/sign_up")
        public ResponseEntity<String> createUser(@RequestBody CreateUserRequestDto user) {


        return ResponseEntity.ok(usersService.save(user));

        }

    @PostMapping("/user/sign_in")
    public String generateToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                authRequest.getPassword()));

        if(authenticate.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());

        } else  {
            throw  new UsernameNotFoundException("user is not found in data base");
        }
    }


    @GetMapping("/user")
    public ResponseEntity<ResposneUserDto> getProfileDetails(Principal principal) throws UserNameNotLoaded {

        String userName = principal.getName();

        ResposneUserDto resposneUserDto = usersService.getProfileDetails(userName);
        return new ResponseEntity<>(resposneUserDto, HttpStatus.FOUND);

    }






}
