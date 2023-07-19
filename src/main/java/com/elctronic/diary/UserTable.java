package com.elctronic.diary;

import jakarta.persistence.*;


@Entity
@Table(name = "electronic_diary")
public class UserTable {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "item")
    private String item;

    @Column(name = "grade")
    private int grade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
