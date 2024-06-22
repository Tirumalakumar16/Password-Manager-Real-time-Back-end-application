package com.passwordmanager.passwords.service;

import com.passwordmanager.passwords.dtos.CreateUserRequestDto;
import com.passwordmanager.passwords.dtos.ResposneUserDto;
import com.passwordmanager.passwords.exceptions.UserNameNotLoaded;
import org.springframework.http.HttpStatusCode;

public interface UsersService {
    String save(CreateUserRequestDto user);

    ResposneUserDto getProfileDetails(String userName) throws UserNameNotLoaded;
}
