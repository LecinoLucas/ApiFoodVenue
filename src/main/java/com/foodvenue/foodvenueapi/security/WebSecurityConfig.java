package com.foodvenue.foodvenueapi.security;

import com.foodvenue.foodvenueapi.service.UsuarioDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioDetailsService).passwordEncoder(new BCryptPasswordEncoder() {
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                boolean result = super.matches(rawPassword, encodedPassword);
                System.out.println("Comparing passwords: " + rawPassword + " <-> " + encodedPassword + ", result: " + result);
                return result;
            }
        });
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
                .antMatchers(HttpMethod.GET, "/api/usuarios/**").authenticated() // Bloquear acesso ao endpoint GET de usuários
                .antMatchers(HttpMethod.PUT, "/api/usuarios/**").authenticated() // Bloquear acesso ao endpoint PUT de usuários
                .antMatchers(HttpMethod.DELETE, "/api/usuarios/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/restaurantes").hasAuthority("RESTAURANTE")
                .antMatchers(HttpMethod.PUT, "/api/restaurantes/**").hasAuthority("RESTAURANTE")
                .antMatchers(HttpMethod.DELETE, "/api/restaurantes/**").hasAuthority("RESTAURANTE")
                .antMatchers("/api/admin/**").hasAuthority("RESTAURANTE")
                .antMatchers("/api/auth/**")
                .permitAll()
                .antMatchers("/api/public/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
