package com.electronic.diary.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "user_data")
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Id
    @Column(name = "user_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemsDTO> items;

    public UserDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserDTO(String username, String email, String password, List items) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.items = items;
    }

    @Override
    public String toString() {
        return new StringBuilder("{\n")
                .append("\t\"ID\": ").append(user_id).append(", \n")
                .append("\t\"Username\": \"").append(username).append("\", \n")
                .append("\t\"E-mail\": \"").append(email).append("\" \n")
                .append("}")
                .toString();
    }

}
