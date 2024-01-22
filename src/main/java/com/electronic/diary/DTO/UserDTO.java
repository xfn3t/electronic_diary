package com.electronic.diary.DTO;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_data")
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public UserDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public UserDTO() {}

    @Override
    public String toString() {
        return "{ID: " + id +
                ", Username: " + username +
                ", E-mail: " + email + "}";
    }

}
