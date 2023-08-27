package com.elctronic.diary.DTO;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
}
