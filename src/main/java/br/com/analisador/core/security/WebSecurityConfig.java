package br.com.analisador.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/empresas/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/api/empresas/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/empresas/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/empresas/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/api/usuarios/**").hasAnyRole("ADMIN", "USER", "CUSTOM")
                .antMatchers(HttpMethod.POST, "/api/usuarios/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/api/usuarios/**").hasAnyRole("ADMIN", "USER", "CUSTOM")
                .antMatchers(HttpMethod.DELETE, "/api/usuarios/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/api/pesquisas/**").hasAnyRole("ADMIN", "USER", "CUSTOM")
                .antMatchers(HttpMethod.POST, "/api/pesquisas/**").hasAnyRole("ADMIN", "USER", "CUSTOM")
                .antMatchers(HttpMethod.GET, "/").hasAnyRole("ADMIN", "USER", "CUSTOM")
                .antMatchers(HttpMethod.PUT, "/empresa/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/usuarios/**").hasAnyRole("ADMIN", "USER", "CUSTOM")
                .antMatchers(HttpMethod.POST, "/usuarios/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/usuarios/**").hasAnyRole("ADMIN", "USER", "CUSTOM")
                .antMatchers(HttpMethod.DELETE, "/usuarios/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/pesquisas/**").hasAnyRole("ADMIN", "USER", "CUSTOM")
                .antMatchers(HttpMethod.POST, "/pesquisas/**").hasAnyRole("ADMIN", "USER", "CUSTOM")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .realmName("API Realm") // Nome do reino para autenticação Basic
                .and()
                .formLogin()
                .loginPage("/login") // Define a página de login personalizada
                .defaultSuccessUrl("/", true)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
                .headers().frameOptions().disable() // Permite o acesso ao H2 Console
                .and()
                .csrf().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(this.passwordEncoder().encode("admin123"))
                .roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
