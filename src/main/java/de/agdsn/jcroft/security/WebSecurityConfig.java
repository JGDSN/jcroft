package de.agdsn.jcroft.security;

import de.agdsn.jcroft.api.v1.token.APIv1UserLogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;
    @Autowired
    APIv1UserLogoutHandler apIv1UserLogoutHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                    .invalidSessionUrl("/sessionInvalid")
                    .maximumSessions(1)
                        .expiredUrl("/sessionExpired")
                        .maxSessionsPreventsLogin(false)
                    .and()
                .and().formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                .and().logout()
                    .logoutSuccessHandler(apIv1UserLogoutHandler)
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/logoutSuccess");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationProvider);
    }

}