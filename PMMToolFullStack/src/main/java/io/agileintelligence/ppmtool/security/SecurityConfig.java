package io.agileintelligence.ppmtool.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable() // Not important polices, using JWT
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement()
                // STATELESS: No state on the server
                // for Rest API, server do not have to hold the session or create cookies
                // Request coming with a valid token => server is going to be responded
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().frameOptions().sameOrigin() // To enable H2 Database
                .and()
                .authorizeRequests()
                .antMatchers( // Enable access to public resources
                        "/",
                        "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg",
                        "/**/*.html", "/**/*.css", "/**/*.js"
                ).permitAll()
                .antMatchers("/api/users/**").permitAll()
                .anyRequest().authenticated(); // Anything other than above config, need to have authentication
    }
}
