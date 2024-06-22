package com.passwordmanager.passwords.service;

import com.passwordmanager.passwords.dtos.CreateUserRequestDto;
import com.passwordmanager.passwords.dtos.ResposneUserDto;
import com.passwordmanager.passwords.exceptions.UserNameNotLoaded;
import com.passwordmanager.passwords.models.Role;
import com.passwordmanager.passwords.models.Users;
import com.passwordmanager.passwords.repositories.UsersRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UsersService{

    private UsersRepo repository;

    private PasswordEncoder enconder;

    private ModelMapper modelMapper;
    @Autowired
    public UserServiceImpl(UsersRepo repository, PasswordEncoder enconder, ModelMapper modelMapper) {
        this.repository = repository;
        this.enconder = enconder;
        this.modelMapper = modelMapper;
    }

    @Override
    public String save(CreateUserRequestDto user) {

        Users user1 = new Users();

        if(user.getFirst_name().length()<3) {
            throw new IllegalArgumentException("Please enter Valid Name");
        }
        user1.setFirst_name(user.getFirst_name());
        user1.setLast_name(user.getLast_name());
        user1.setUserName(user.getUserName());
        user1.setPassword(enconder.encode(user.getPassword()));
        user1.setActive(true);
        user1.setEmail(user.getEmail());
        user1.setCreated_At(new Date());
        user1.setUpdated_At(new Date());
        user1.setRoles(user.getRoles());
        if(user.getPhone().length() <10){
            throw new IllegalArgumentException("Please enter Valid Phone number");
        }
        user1.setPhone(user.getPhone());
        Random random = new Random();
        user1.setUser_id(user.getFirst_name().substring(0,5)+random.nextInt(10000));

        repository.save(user1);

        return "User Created Successfully userId is '"+user1.getUser_id()+"'";
    }

    @Override
    public ResposneUserDto getProfileDetails(String userName) throws UserNameNotLoaded {
        if (userName.isEmpty()){
                throw new UserNameNotLoaded("Username not loaded by principle");
        }
        Optional<Users> user = repository.findByUserName(userName);
        if (user.isEmpty()) {
            throw new UserNameNotLoaded("User Not found");
        }

        return modelMapper.map(user.get(),ResposneUserDto.class);


    }
}
