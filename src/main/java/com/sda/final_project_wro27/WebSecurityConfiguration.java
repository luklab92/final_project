package com.sda.final_project_wro27;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.List;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/register","/register/*")
                .permitAll()
                .antMatchers("/login")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login-process")
                .failureUrl("/login?error=1")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutUrl("/logout") // jesli wyslemy request na ten url zostaniemy wylogowani
                .logoutSuccessUrl("/login"); // na ten url zostaniemy przekierowani po wylogowanjiu
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery("select u.LOGIN,u.PASSWORD, 1 from USER u where u.LOGIN=?")
                .authoritiesByUsernameQuery("select u.LOGIN, R.ROLE, 1 from USER u \n" +
                        "join USER_ROLES UR on u.ID = UR.USER_ID\n" +
                        "join USER_ROLE R on R.ID = UR.ROLES_ID\n" +
                        "where u.LOGIN = ?\n")
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);
        auth.inMemoryAuthentication()
                .withUser("user@user.pl")
                .password(passwordEncoder.encode("haslo"))
                .roles("USER")
                .and()
                .withUser("admin@admin.pl")
                .password(passwordEncoder.encode("haslo"))
                .roles("ADMIN");

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
