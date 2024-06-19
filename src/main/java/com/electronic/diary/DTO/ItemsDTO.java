package com.electronic.diary.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "items")
public class ItemsDTO {

    @Id
    @Column(name = "items_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long items_id;

    @Column(name = "item_name")
    private String item_name;

    @Column(name = "item_content")
    private String item_content;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDTO user;

    public ItemsDTO() {}

    public ItemsDTO(Long items_id, String item_name, String item_content, UserDTO user) {
        this.items_id = items_id;
        this.item_name = item_name;
        this.item_content = item_content;
        this.user = user;
    }

    @Override
    public String toString() {
        return new StringBuilder("{\n")
                .append("\t\"Item_ID\": ").append(items_id).append(", \n")
                .append("\t\"Item_name\": \"").append(item_name).append(items_id).append("\", \n")
                .append("\t\"Item_content\": \"").append(item_content).append(items_id).append("\", \n")
                .append("\t\"User_ID\": ").append(user.getUser_id()).append("\n")
                .append("}")
                .toString();
    }

}
