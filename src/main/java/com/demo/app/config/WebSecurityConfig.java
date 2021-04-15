package com.demo.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.demo.app.service.UserDetailsServiceImpl;
/**
 * Class name: WebSecurityConfig
 *
 * Description: WebSecurityConfig
 * 
 *
 * Company: Task
 *
 * @authorArtur Korra
 * @date 23/jan/2020
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// PREVENT SECURITY FORM RESOUCES FILES
	String[] resources = new String[] { "/navigation/**", "/css/**", "/static/**", "/icons/**", "/img/**", "/js/**",
			"/layer/**" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(resources).permitAll()
				.antMatchers("/", "login","/api/service/usersJSON").permitAll()
				.antMatchers("sortUsername", "sortLastname", "deleteUser" ,"edit" ,"saveEdit", "addUser")
				.access("hasRole('ROLE_ADMIN')").antMatchers("/user*").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
				.defaultSuccessUrl("/menu").failureUrl("/login?error=true").usernameParameter("username")
				.passwordParameter("password").and().logout().permitAll().logoutSuccessUrl("/login?logout");
		//http.csrf().disable();
		//disable spring-security login screen      
		http.httpBasic().disable();
	}

	BCryptPasswordEncoder bCryptPasswordEncoder;

	//Create the password encryption
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
		// The number 4 represents how strong you want the encryption.
		// It can be in a range between 4 and 31.
		// If you don't put a number the program will use one randomly each time
		return bCryptPasswordEncoder;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/static/**","/api/service/usersJSON/**");//for static files and API
	}

	@Autowired
	UserDetailsServiceImpl userDetailsService;
    //Register the service for users and the password encryption.
	
	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {

		// Setting Service to find User in the database.
		// And Setting PasswordEncoder
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}
}