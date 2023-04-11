package com.example.frontend;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class MvcConfiguration extends WebSecurityConfigurerAdapter {
   
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
// The pages does not require login
		http.authorizeRequests().antMatchers("/", "/loginPage","/forgetPasswordPage","/registerPage", "/signout", "/viewAllRooms","/viewAllRoomsByButton").permitAll();
// /userInfo page requires login as ROLE_USER or ROLE_ADMIN.
// If no login, it will redirect to /login page.
//		http.authorizeRequests().antMatchers("/addRoom").access("hasAnyRole('ROLE_ADMIN')");
// When the user has logged in as XX.
// But access a page that requires role YY,
// AccessDeniedException will be thrown.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		http.authorizeRequests().and().formLogin()//
//Submit URL of login page.
				.loginProcessingUrl("/j_spring_security_check")// Submit URL/action form
				.loginPage("/login")//
				.defaultSuccessUrl("/home")//
				.failureUrl("/login?error=true")
//Config for Logout Page
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").and().sessionManagement()
				.sessionFixation().newSession().maximumSessions(1).maxSessionsPreventsLogin(true)
				.expiredUrl("/login?expired").and().invalidSessionUrl("/login?invalid")
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
	}

}