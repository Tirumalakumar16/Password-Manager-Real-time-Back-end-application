package com.passwordmanager.passwords.controllers;

import com.passwordmanager.passwords.dtos.ChangePasswordRequestDto;
import com.passwordmanager.passwords.dtos.DeleteRequest;
import com.passwordmanager.passwords.dtos.PasswordRequestDto;
import com.passwordmanager.passwords.dtos.PasswordResponseDto;
import com.passwordmanager.passwords.service.PasswordsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/password.manager.com")
public class PasswordController {


    private PasswordsService passwordsService;

    public PasswordController(PasswordsService passwordsService) {
        this.passwordsService = passwordsService;
    }

    @PostMapping("/password")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> savePassword(Principal principal, @RequestBody PasswordRequestDto passwordRequestDto) {

        String userName = principal.getName();

        return ResponseEntity.ok(passwordsService.saveData(userName,passwordRequestDto));
    }

    @GetMapping("/passwords")
    public List<PasswordResponseDto> getAllPasswords(Principal principal) {

        String userName = principal.getName();

        return passwordsService.findAll(userName);
    }
    @PatchMapping("/password")
    public ResponseEntity<String> UpdatePasswords(@RequestBody ChangePasswordRequestDto requestDto) {
        return ResponseEntity.ok(passwordsService.update(requestDto));
    }

    @DeleteMapping("/password")
    public ResponseEntity<String> DeletePassword(@RequestBody DeleteRequest deleteRequest,Principal principal) {

        String userName = principal.getName();

        return ResponseEntity.ok(passwordsService.deletePassword(userName,deleteRequest));
    }





}
