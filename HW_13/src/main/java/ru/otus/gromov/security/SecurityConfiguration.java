package ru.otus.gromov.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.otus.gromov.service.UserService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Autowired
    public SecurityConfiguration(UserService userService) {
        this.userService = userService;
    }


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/security_check", "/*.svg", "/webjars*/**", "/h2-console*/**", "/login")
                .permitAll()

                .and()
                .authorizeRequests().antMatchers("/books/**", "/genres/**", "/authors/**").authenticated()

                .and()
                .authorizeRequests().antMatchers("/swagger-ui*/**").hasRole("ADMIN")

                .and()
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/security_check")
                .usernameParameter("x_username")
                .passwordParameter("x_password")


                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/")

                .and()
                .rememberMe().key("someSecret")
                .and().exceptionHandling().accessDeniedPage("/login");
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) {
        userService.getAll().forEach((user -> {
            try {
                auth.inMemoryAuthentication()
                        .withUser(user.getUserName()).password(user.getPassword()).roles(user.getRole());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return charSequence.toString().equals(s);
            }
        };
    }


}
