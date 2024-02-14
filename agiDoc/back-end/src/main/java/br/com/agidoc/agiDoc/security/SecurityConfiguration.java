package br.com.agidoc.agiDoc.security;

import br.com.agidoc.agiDoc.service.TokenService;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final TokenService tokenService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and()
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                                .antMatchers("/user/login").permitAll()
                        .antMatchers("/user/get-logged-user").permitAll()
                                .antMatchers(HttpMethod.POST, "/company/**").hasAnyRole("ADMIN_INSTITUTION",
                        "USER_INSTITUTION")
                        .antMatchers(HttpMethod.GET, "/company/**").hasRole("ADMIN_INSTITUTION")
                        .antMatchers(HttpMethod.GET, "/company/actives").hasAnyRole("ADMIN_INSTITUTION",
                        "USER_INSTITUTION")
                        .antMatchers(HttpMethod.PUT, "/company/**").hasRole("ADMIN_INSTITUTION")
                        .antMatchers(HttpMethod.DELETE, "/company/**").hasRole("ADMIN_INSTITUTION")
                        // PROCESS
                        .antMatchers(HttpMethod.POST, "/process/**").hasRole("ADMIN_INSTITUTION")
                        .antMatchers(HttpMethod.GET, "/process/**").permitAll()
                        .antMatchers(HttpMethod.PUT, "/process/**").hasRole("ADMIN_INSTITUTION")
                        .antMatchers(HttpMethod.DELETE, "/process/**").hasRole("ADMIN_INSTITUTION")
                        // DOCUMENT
                        .antMatchers(HttpMethod.POST, "/document/**").hasAnyRole("ADMIN_INSTITUTION",
                        "ADMIN_COMPETITOR", "USER_COMPETITOR", "USER_INSTITUTION")
                        .antMatchers(HttpMethod.GET, "/document/**").permitAll()
                        .antMatchers(HttpMethod.PUT, "/document/**").hasAnyRole("ADMIN_INSTITUTION",
                        "ADMIN_COMPETITOR", "USER_COMPETITOR", "USER_INSTITUTION")
                        .antMatchers(HttpMethod.DELETE, "/document/**").hasAnyRole("ADMIN_INSTITUTION",
                        "ADMIN_COMPETITOR")
                        // USER
                        .antMatchers(HttpMethod.POST, "/user/**").hasAnyRole("ADMIN_INSTITUTION",
                        "ADMIN_COMPETITOR")
                        .antMatchers(HttpMethod.GET, "/user/**").hasAnyRole("ADMIN_INSTITUTION",
                        "ADMIN_COMPETITOR")
                        .antMatchers(HttpMethod.PUT, "/user/**").hasAnyRole("ADMIN_INSTITUTION",
                        "ADMIN_COMPETITOR")
                        .antMatchers(HttpMethod.DELETE, "/user/**").hasAnyRole("ADMIN_INSTITUTION",
                        "ADMIN_COMPETITOR")
                        // ADDRESS/CONTACTS
                        .antMatchers("/address", "/contact", "address/**", "/contact/**").hasAnyRole("ADMIN_INSTITUTION",
                        "ADMIN_COMPETITOR")
                        .antMatchers("/", "/**").hasRole("SYSTEM_ADMIN")
                        .anyRequest().authenticated()
                );
        http.addFilterBefore(new TokenAuthenticationFilter(tokenService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui/**");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .exposedHeaders("Authorization");
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }
}
