package main.config;

import lombok.RequiredArgsConstructor;
import main.repositories.UserRepository;
import main.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/profile").authenticated()
                .antMatchers("/books/*").authenticated()
                .antMatchers("/books/*").authenticated()
                .antMatchers("/add-book").hasAuthority("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin().permitAll()
                .defaultSuccessUrl("/profile", true)
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .permitAll();

        http.cors().and().csrf().disable();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new MyUserDetailsService(userRepository);
    }
}
