package com.passwordmanager.passwords.repositories;

import com.passwordmanager.passwords.models.Passwords;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PasswordsRepo extends JpaRepository<Passwords,Long> {

    @Query(value = "select * from passwords s where s.email = ?1",nativeQuery = true)
    List<Passwords> findByEmail(String email);

    @Query(value = "select * from passwords s where s.title_address =?1",nativeQuery = true)
    Optional<Passwords> findByTitle_address(String email);

    @Modifying
    @Transactional
    @Query(value = "delete from password_manager.passwords s where s.title_address =?1 and s.title = ?2 and s.email=?3",nativeQuery = true)
    void deleteByEmailAndTitle(String email, String title,String main_mail );
}
