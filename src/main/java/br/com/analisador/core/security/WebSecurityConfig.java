package br.com.analisador.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers(HttpMethod.GET,"/empresas/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/empresas/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/usuarios/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST,"/usuarios/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/usuarios/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE,"/usuarios/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/pesquisas/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST,"/pesquisas/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

