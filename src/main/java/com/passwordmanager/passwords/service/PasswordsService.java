package com.passwordmanager.passwords.service;

import com.passwordmanager.passwords.dtos.ChangePasswordRequestDto;
import com.passwordmanager.passwords.dtos.DeleteRequest;
import com.passwordmanager.passwords.dtos.PasswordRequestDto;
import com.passwordmanager.passwords.dtos.PasswordResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PasswordsService {
    String saveData(String userName,PasswordRequestDto passwordRequestDto);

    List<PasswordResponseDto> findAll(String userName);

    String update( ChangePasswordRequestDto requestDto);

    String deletePassword(String userName, DeleteRequest deleteRequest);
}
