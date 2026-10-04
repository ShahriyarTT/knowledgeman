package com.virtusbellatoris.knowledgeman.DTO;

import jakarta.validation.constraints.NotBlank;

public class PasswordChangeDTO {

    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;

    @NotBlank
    private String confirmNewPassword;

    // Constructors
    public PasswordChangeDTO() {    }

    public PasswordChangeDTO(String currentPassword,
                             String newPassword,
                             String confirmNewPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmNewPassword = confirmNewPassword;
    }

    // Getters
    public String getCurrentPassword() {        return currentPassword;    }
    public String getNewPassword() {        return newPassword;    }
    public String getConfirmNewPassword() {        return confirmNewPassword;    }
}
