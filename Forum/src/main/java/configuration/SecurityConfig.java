package configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	protected void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("huypham").password("111111").roles("GOD");
		auth.inMemoryAuthentication().withUser("luffy").password("222222").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("creps").password("333333").roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/home").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/god/**").hasRole("GOD")
			.and().formLogin().loginPage("/login").and().exceptionHandling().accessDeniedPage("/access-denied");
	}
	
}
