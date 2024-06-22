package com.passwordmanager.passwords.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestDto {

    private String first_name;
    private String last_name;

    private String email;
    private String phone;
    private String userName;
    private String password;
    private String roles;

}
