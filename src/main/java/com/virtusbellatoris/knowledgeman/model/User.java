package com.virtusbellatoris.knowledgeman.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
@Table(name = "users") // The problem is user is a reserved word in PostgreSQL.
// Hibernate created a table called user but PostgreSQL treats it as a keyword and can't query it properly.
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    // like @NotNull but also rejects empty strings
    @NotBlank
    @Column(nullable = false) // database level, prevents null in PostgreSQL column
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String surname;

    public enum Gender{
        MALE,
        FEMALE
    }

    @NotNull // @NotBlank is for Strings only. Enums can't be blank. Use @NotNull instead:
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String hashedPassword;

    @NotNull
    @Column(nullable = false)
    private LocalDate birthDate;

    @NotBlank
    @Column(nullable = false)// LocalDate.of(2026, 9, 2);
    private String countryOfOrigin;

    @NotNull
    @Column(updatable = false)
    private ZonedDateTime registrationZonedDateTime;

    @NotBlank
    @Column(updatable = false)
    private String registrationIp;

    public enum UserType{
        ADMIN,
        REGULAR
    }

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    // Constructors
    protected User() {    }

    public User(String name,
                String surname,
                Gender gender,
                String email,
                LocalDate birthDate,
                String countryOfOrigin,
                // UserType userType,
                String registrationIp) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.email = email;
        this.birthDate = birthDate;
        this.countryOfOrigin = countryOfOrigin;
        // this.userType = userType;
        this.userType = UserType.REGULAR;
        this.registrationIp = registrationIp;
        this.registrationZonedDateTime = ZonedDateTime.now();
    }

    // Getters
    public Integer getId() {        return id;    }
    public String getName() {        return name;    }
    public String getSurname() {        return surname;    }
    public Gender getGender() {        return gender;    }
    public String getDisplayName() {        return this.name + " " + this.surname;    }
    public String getEmail() {        return email;    }

    @JsonIgnore
    public String getHashedPassword() {        return hashedPassword;    }

    public LocalDate getBirthDate() {        return birthDate;    }
    public String getCountryOfOrigin() {        return countryOfOrigin;    }
    public ZonedDateTime getRegistrationZonedDateTime() {        return registrationZonedDateTime;    }
    public String getRegistrationIp() {        return registrationIp;    }
    public UserType getUserType() {        return userType;    }

    // Setters
    public void setName(String name) {        this.name = name;    }
    public void setSurname(String surname) {        this.surname = surname;    }
    public void setGender(Gender gender) {        this.gender = gender;    }
    public void setEmail(String email) {        this.email = email;    }
    public void setHashedPassword(String hashedPassword) {        this.hashedPassword = hashedPassword;    }
    public void setBirthDate(LocalDate birthDate) {        this.birthDate = birthDate;    }
    public void setCountryOfOrigin(String countryOfOrigin) {        this.countryOfOrigin = countryOfOrigin;    }
    public void setUserType(UserType userType) {        this.userType = userType;    }


}
