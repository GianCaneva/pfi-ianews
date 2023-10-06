package com.uade.ainews.newsGeneration.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Arrays;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    private UserDetailsService userDetailsService;
    private JWTAuthorizationFilter jwtAuthorizationFilter;
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();

        dispatcherServlet.setEnableLoggingRequestDetails(true); // Habilitar la impresión detallada de solicitudes

        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        //return http.authorizeRequests().anyRequest().permitAll().and().cors().and().csrf().disable().build();

        // .requestMatchers("user/register/").permitAll()

//  .authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests
//                                .antMatchers("/ruta/directorio/especifico/**").permitAll() // Esta es la regla para permitir el acceso sin autenticación
//                                .anyRequest().authenticated()
//                )


        return http
        .csrf().disable()
        .authorizeHttpRequests()
        .requestMatchers("user/register/**").permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilter(jwtAuthenticationFilter)
        .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();


    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
/*  Se usa para probar algunos usuarios en memoria

    @Bean
    UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles()
                .build());
        return manager;
    }
*/
    @Bean
    AuthenticationManager authManager (HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return Encoder.getInstance();
    }

    //Usar para generar contraseñas encriptadas
    /*
    public static void main (String[] args){
        Encoder encoder = Encoder.getInstance();
        System.out.println("pass: " + encoder.encode("admin") );
    }

     */


}
