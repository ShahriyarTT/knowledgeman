package com.virtusbellatoris.knowledgeman.DTO;

import jakarta.validation.constraints.NotBlank;

public class LoginDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    // Constructors
    public LoginDTO() {    }

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters
    public String getEmail() {        return email;    }
    public String getPassword () {        return password;    }
}
