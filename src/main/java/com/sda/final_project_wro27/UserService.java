package com.sda.final_project_wro27;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void registerUser(RegistrationDto registrationDto) {
        String passwordHash = String.valueOf(registrationDto.getPassword().hashCode());

        if (userWithEmailExists(registrationDto.getLogin())) {
            throw new RuntimeException("Użytkownik o takim emailu " + registrationDto.getLogin() + "istnieje");
        }
        User userToSave = User.apply(registrationDto,passwordHash);
        userRepository.save(userToSave);

    }

    private boolean userWithEmailExists(String login) {
        return userRepository.existsByLogin(login);
    }
}
