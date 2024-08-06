package com.electronic.diary.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "items")
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @Column(name = "items_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemsId;

    @Column(name = "item_name")
    private String item_name;

    @Column(name = "item_content")
    private String item_content;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
