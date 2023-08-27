package com.elctronic.diary;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_data")
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;
}
