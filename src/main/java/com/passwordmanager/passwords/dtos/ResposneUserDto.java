package com.passwordmanager.passwords.dtos;

import com.passwordmanager.passwords.models.Role;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResposneUserDto {


    private String user_id;
    private String userName;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String roles;
    private boolean isActive;
}
