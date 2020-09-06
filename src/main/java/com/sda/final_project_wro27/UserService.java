package com.sda.final_project_wro27;

import com.sda.final_project_wro27.model.UserEntity;
import com.sda.final_project_wro27.dto.RegistrationDto;
import com.sda.final_project_wro27.repository.UserRepository;
import com.sda.final_project_wro27.repository.UserRoleRepository;
import com.sda.final_project_wro27.repository.UserStatusRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserStatusRepository userStatusRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, UserStatusRepository userStatusRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository=userRoleRepository;
        this.userStatusRepository = userStatusRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void registerUser(RegistrationDto registrationDto) {
        String password = passwordEncoder.encode(registrationDto.getPassword());
        if (userWithEmailExists(registrationDto.getLogin())) {
            throw new RuntimeException("Użytkownik o takim emailu " + registrationDto.getLogin() + "istnieje");
        }
        UserEntity userToSave = UserEntity.apply(registrationDto,password);
        userToSave.addRole(userRoleRepository.findByRoleName(UserRole.Roles.USER.name()));
        userToSave.addStatus(userStatusRepository.findByStatusByName(UserStatus.Status.NOT_ACCEPTED.name()));
        userRepository.save(userToSave);
        System.out.println(userToSave);
    }

    private boolean userWithEmailExists(String login) {
        return userRepository.existsByLogin(login);
    }

    public UserEntity findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    public boolean checkIfTextIsNotNumeric(String text) {
        String pattern1="[0-9]";

       return !regexMatcher(text,pattern1);

    }
    public boolean validEmail(String email) {
        String emailValidatorRegex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        return regexMatcher(email, emailValidatorRegex);
    }
    public boolean validZipCode (String zipCode) {
        String zipRegex = "^[0-9]{2}-[0-9]{3}$";
        return regexMatcher(zipCode,zipRegex);
    }

    public boolean validPhoneNumber (String phoneNumber) {
        String phoneRegex = "^+[0-9]{2} [0-9]{9}";
        return regexMatcher(phoneNumber,phoneRegex);
    }

    public boolean validPesel(String pesel) {
            int sum=0;
            int[] weights = {9,7,3,1,9,7,3,1,9,7};
            if (pesel.length()==11) {
                for (int i = 0; i < pesel.length() - 1; i++) {
                    sum = sum + pesel.charAt(i) + weights[i];
                }
            }
            return sum%10==pesel.charAt(pesel.length()-1);
    }

    private boolean regexMatcher(String input, String regex) {
        Pattern pattern =  Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }




}
