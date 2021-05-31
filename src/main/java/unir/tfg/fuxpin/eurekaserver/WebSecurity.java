package unir.tfg.fuxpin.eurekaserver;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * Spring Security Configuration
 *
 * @author Xavier Rodríguez
 *
 */

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()   // Eureka clients will not generally possess a valid cross site request forgery (CSRF) token you will need to disable this requirement
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}