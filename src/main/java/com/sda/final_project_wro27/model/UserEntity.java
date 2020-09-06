package com.sda.final_project_wro27.model;

import com.sda.final_project_wro27.*;
import com.sda.final_project_wro27.dto.RegistrationDto;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class UserEntity extends BaseEntity {

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
    @ManyToOne
    private UserStatus status;

    public static UserEntity apply(RegistrationDto registrationDto, String passwordHash){
        UserEntity user = new UserEntity();
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

        if (hasAnyRole(userRole)) {
            return;
        }
        if (roles == null) {
            roles = new ArrayList<>();
        }
        roles.add(userRole);
    }
    public void addStatus(UserStatus byStatusByName) {
        status = byStatusByName;
    }


    private boolean hasAnyRole(UserRole userRole) {
        return roles != null && roles.stream().anyMatch(r -> userRole.getRole().equals(r.getRole()));
    }



    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roles=" + roles +
                ", status=" + status +
                '}';
    }




}
