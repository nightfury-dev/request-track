package framgia.co.edu.ftrr.config;

import framgia.co.edu.ftrr.common.Roles;
import framgia.co.edu.ftrr.config.filter.JWTAuthenticationFilter;
import framgia.co.edu.ftrr.config.filter.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySource(value = "classpath:messages.properties", encoding = "UTF-8")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final String ROLE_SM = String.valueOf(Roles.SM.getCode());
    private final String ROLE_HR = String.valueOf(Roles.HR.getCode());
    private final String ROLE_EC = String.valueOf(Roles.EC.getCode());
    private final String ROLE_TRAINER = String.valueOf(Roles.TRAINER.getCode());
    private final String ROLE_GL = String.valueOf(Roles.GL.getCode());
    private final String ROLE_DM = String.valueOf(Roles.DM.getCode());
    private final String other = String.valueOf(Roles.OTHER.getCode());

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .and()
                .authorizeRequests().antMatchers("/div/**").hasAnyRole(ROLE_DM, ROLE_SM)
                .and()
                .authorizeRequests().antMatchers("/edu/**").hasAnyRole(ROLE_EC, ROLE_TRAINER)
                .and()
                .authorizeRequests().antMatchers("/hr/**").hasAnyRole(ROLE_HR)
                .and()
                .authorizeRequests().antMatchers("/requests/**/interviews/**").hasRole(ROLE_GL)
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email,encrypted_password,enabled from users where email=?")
                .authoritiesByUsernameQuery("select email,role from users where email=?")
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs/**");
        web.ignoring().antMatchers("/swagger.json");
        web.ignoring().antMatchers("/swagger-ui.html");
        web.ignoring().antMatchers("/swagger-resources/**");
        web.ignoring().antMatchers("/webjars/**");
    }
}
