package cat.tecnocampus.tinder2425.security.configuration;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL = {
            "/authenticate",
            "/h2-console/**",
            "/webjars/**",
            "/v3/api-docs/**", //this is for swagger
            "/swagger-ui/**",
            "/swagger-ui.html"};
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions
                        .sameOrigin()))   // This is to allow the h2-console to be used in the browser. It allows the browser to render the response in a frame.
                .csrf(csrf -> csrf.disable()) //This is to disable the csrf protection. It is not needed for this project since the application is satateless (and we are using JWT)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL).permitAll()
                                .requestMatchers("/teaching/**").permitAll()
                                .requestMatchers("/quotes/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/profiles/me/**").hasRole("USER")
                                .requestMatchers("/profiles/**", "/profilesByName/**").hasRole("ADMIN")
                                .requestMatchers(POST, "/profiles").hasRole("ADMIN")

                                .anyRequest()
                                .authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
