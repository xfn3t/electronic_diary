package com.electronic.diary.DTO;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_data")
public class UserDTO {

    @Id
    @Column(name = "user_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

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
        return new StringBuilder("{ID: ").append(user_id)
                .append(", Username: ").append(username)
                .append(", E-mail: ").append(email)
                .append("}")
                .toString();
    }

}
