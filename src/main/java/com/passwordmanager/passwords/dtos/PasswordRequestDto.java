package com.passwordmanager.passwords.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRequestDto {

    private String title;
    private String title_address;
    private String password;
    private String remarks;
}
