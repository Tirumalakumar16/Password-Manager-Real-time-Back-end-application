package com.passwordmanager.passwords.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResponseDto {

    private ResposneUserDto resposneUserDto;

    private String title;
    private String title_address;
    private String password;
    private String email;
    private String remarks;


}
