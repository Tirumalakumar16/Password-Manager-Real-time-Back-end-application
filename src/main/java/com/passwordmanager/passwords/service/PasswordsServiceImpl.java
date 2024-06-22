package com.passwordmanager.passwords.service;

import com.passwordmanager.passwords.dtos.*;
import com.passwordmanager.passwords.models.Passwords;
import com.passwordmanager.passwords.models.Users;
import com.passwordmanager.passwords.repositories.PasswordsRepo;
import com.passwordmanager.passwords.repositories.UsersRepo;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PasswordsServiceImpl implements PasswordsService{

    private PasswordsRepo passwordsRepo;

    private UsersRepo usersRepo;

    private ModelMapper modelMapper ;

    public PasswordsServiceImpl(PasswordsRepo passwordsRepo, UsersRepo usersRepo, ModelMapper modelMapper) {
        this.passwordsRepo = passwordsRepo;
        this.usersRepo = usersRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public String saveData(String userName,PasswordRequestDto passwordRequestDto) {

        Optional<Users> user = usersRepo.findByUserName(userName);

        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User not loaded please check");
        }

        Passwords passwords = new Passwords();
        passwords.setPassword(passwordRequestDto.getPassword());
        passwords.setEmail(user.get().getEmail());
        passwords.setTitle(passwordRequestDto.getTitle());
        passwords.setRemarks(passwordRequestDto.getRemarks());
        passwords.setTitle_address(passwordRequestDto.getTitle_address());
        passwords.setCreated_At(new Date());
        passwords.setUpdated_At(new Date());

        passwordsRepo.save(passwords);

        return "Password saved Successfully";

    }

    @Override
    public List<PasswordResponseDto> findAll(String userName) {

        Optional<Users> users = usersRepo.findByUserName(userName);
        if(users.isEmpty()) {
            throw new UsernameNotFoundException("User not loaded please check");
        }

        List<Passwords> passwordsList = passwordsRepo.findByEmail(users.get().getEmail());

        ResposneUserDto resposneUserDto = modelMapper.map(users.get(), ResposneUserDto.class);

        List<PasswordResponseDto> passwordResponseDtoList = List.of(modelMapper.map(passwordsList, PasswordResponseDto[].class));

        passwordResponseDtoList.get(0).setResposneUserDto(resposneUserDto);

        return passwordResponseDtoList;

    }

    @Override
    public String update( ChangePasswordRequestDto requestDto) {



        Passwords passwords = passwordsRepo.findByTitle_address(requestDto.getEmail()).get();

        passwords.setPassword(requestDto.getPassword());
        passwords.setUpdated_At(new Date());

        passwordsRepo.save(passwords);

        return "Updated the password";

    }

    @Override
    public String deletePassword(String userName, DeleteRequest deleteRequest) {

        Optional<Users> users = usersRepo.findByUserName(userName);
        if(users.isEmpty()) {
            throw new UsernameNotFoundException("User not loaded please check");
        }
        String main_Mail = users.get().getEmail();

        passwordsRepo.deleteByEmailAndTitle(deleteRequest.getEmail(),deleteRequest.getTitle(),main_Mail);

        return "deleted successfully";
    }
}
