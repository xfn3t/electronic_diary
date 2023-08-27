package com.elctronic.diary.DTO;

import lombok.Data;

@Data
public class SignUpRequest {
    private String email;
    private String username;
    private String password;
}
