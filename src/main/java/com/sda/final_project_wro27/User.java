package com.sda.final_project_wro27;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends BaseEntity{

    private String firstName;
    private String lastName;
    @Embedded
    private Address address;
    private LocalDate birthDate;
    private String pesel;
    private String login;
    private String password;
    private String phoneNumber;
    private boolean preferEmails;
    @ManyToMany
    private List<UserRole> roles;

    public static User apply(RegistrationDto registrationDto, String passwordHash){
        User user = new User();
        Address address = Address.apply(registrationDto);
        user.firstName = registrationDto.getFirstName();
        user.lastName = registrationDto.getLastName();
        user.login = registrationDto.getLogin();
        user.password = passwordHash;
        user.address = address;
        user.birthDate = LocalDate.parse(registrationDto.getBirthDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return user;

    }

    public void addRole(UserRole userRole) {

        if (isaBoolean(userRole)) {
            return;
        }
        if (roles == null) {
            roles = new ArrayList<>();
        }
        roles.add(userRole);
    }

    private boolean isaBoolean(UserRole userRole) {
        return roles != null && roles.stream().anyMatch(r -> userRole.getRole().equals(r.getRole()));
    }

}
