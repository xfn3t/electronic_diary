package com.electronic.diary.DTO;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_tables")
public class UserTableDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name")
    private String item_name;

    @Column(name = "item_content")
    private String item_content;

    @Column(name = "user_id")
    private Long user_id;
}
