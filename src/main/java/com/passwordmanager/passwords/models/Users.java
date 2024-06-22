package com.passwordmanager.passwords.models;

import jakarta.persistence.*;
import lombok.*;
import org.aspectj.weaver.EnumAnnotationValue;

import java.util.Date;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table( uniqueConstraints = {

        @UniqueConstraint(
                name ="email",
                columnNames = "email"
        ),
        @UniqueConstraint(
                name = "phone",
                columnNames = "phone"
        ),
        @UniqueConstraint(
                name = "user_id",
                columnNames = "user_id"
        )
})
public class Users {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        @NonNull
        private String user_id;
        private String userName;
        private String password;

        private String first_name;
        private String last_name;
        private String email;
        private String phone;
        private Date created_At;
        private Date updated_At;

        private String roles;
        private boolean isActive;

}
