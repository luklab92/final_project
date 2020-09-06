package com.sda.final_project_wro27;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringConfig {

    @Bean
    public PasswordEncoder passwordEncoderBCrypt(){
        return new BCryptPasswordEncoder();
    }
}
