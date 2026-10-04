package com.virtusbellatoris.knowledgeman.DTO;

import com.virtusbellatoris.knowledgeman.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class UserDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotNull
    private User.Gender gender;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private LocalDate birthDate;

    @NotBlank
    private String countryOfOrigin;


    // Constructors
    public UserDTO() {    }

    public UserDTO(String name,
                   String surname,
                   User.Gender gender,
                   String email,
                   String password,
                   LocalDate birthDate,
                   String countryOfOrigin

                   ) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.countryOfOrigin = countryOfOrigin;
    }

    // Getters
    public String getName() {        return name;    }
    public String getSurname() {        return surname;    }
    public User.Gender getGender() {        return gender;    }
    public String getEmail() {        return email;    }
    public String getPassword() {        return password;    }
    public LocalDate getBirthDate() {        return birthDate;    }
    public String getCountryOfOrigin() {        return countryOfOrigin;    }


}
