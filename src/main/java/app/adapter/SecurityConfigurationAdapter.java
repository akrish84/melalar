package app.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableWebSecurity
public class SecurityConfigurationAdapter extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailService;

//	Method where we can set up and format our own authentication manager using the builder argument.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("Building authentication manager");
		auth.userDetailsService(userDetailService);
	}

//	Add roles to different users and permit them accordingly.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.authorizeRequests()
		.antMatchers("/").hasAnyRole("ADMIN", "USER")
		.antMatchers("/admin").permitAll()
		.and().formLogin();
	}

	@Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
		
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
                .allowedMethods("*");
			}
		};
	}

}
