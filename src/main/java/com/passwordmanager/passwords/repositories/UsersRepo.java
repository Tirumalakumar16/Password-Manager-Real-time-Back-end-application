package com.passwordmanager.passwords.repositories;

import com.passwordmanager.passwords.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users,Long> {


    Optional<Users> findByUserName(String username);
}
