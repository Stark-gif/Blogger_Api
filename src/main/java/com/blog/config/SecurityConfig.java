package com.blog.config;

//import com.blog.security.CustomUserDetailsService;
import com.blog.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String[] public_URLS ={
            "/v3/api-docs",
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**"

    };

    @Autowired
    private CustomUserDetailsService userDetailsService;


//@Autowired
//    private PasswordEncoder getpasswordEncoder;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, public_URLS).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(getpasswordEncoder());
    }
    @Bean
    public PasswordEncoder getpasswordEncoder(){

        return new BCryptPasswordEncoder();
    }

//        @Override
//@Bean
//     protected UserDetailsService userDetailsService() {
//
////    object's consisting of username and password
//
//     UserDetails user = User.builder().username("tashfeen").password(passwordEncoder().encode("testing")).roles("USER").build();
//     UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("Admin")).roles("ADMIN").build();
//
//     return new InMemoryUserDetailsManager(user,admin);
//     }


}


