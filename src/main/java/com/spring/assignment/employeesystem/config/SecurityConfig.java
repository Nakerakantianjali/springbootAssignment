package com.spring.assignment.employeesystem.config;

import com.sun.xml.bind.v2.runtime.output.Encoded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    UserDetailsService userDetailsService;
    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService){
        this.userDetailsService=userDetailsService;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                System.out.println("\n"+"char---sequence"+charSequence+"\n");
                return null;
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                System.out.println("\n"+"matches "+charSequence+" "+s);
                if(charSequence.equals(s)==true){
                    return true;
                }
                return false;
            }
        };
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

       System.out.println("******************configuring--- ");

        auth.userDetailsService(userDetailsService);
     //   auth.jdbcAuthentication().dataSource(dataSource);
       // super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*System.out.println("I am in configure !!");
        http.authorizeRequests().anyRequest().authenticated().and().formLogin().
                loginPage("/showMyLoginPage").permitAll();*/
        /*http.authorizeRequests()
            .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").usernameParameter("email").permitAll().and().logout().permitAll();
*/
        /*http.authorizeRequests().
                antMatchers("/").hasRole("admin")

                .and().formLogin().
                loginPage("/showMyLoginPage").permitAll();*/
       // super.configure(http);
       /* http.authorizeRequests().anyRequest().authenticated().and().formLogin().
                loginPage("/login").loginProcessingUrl("/authenticateTheUser").permitAll();*/
             // System.out.println("hey you are authenticationg");
        http.authorizeRequests().antMatchers("/").hasAnyAuthority("admin").
                antMatchers("/employee/list").hasAnyAuthority("admin","TeamLead").
                antMatchers("/employee/profile").hasAnyAuthority("admin","TeamLead","TeamMember").
                antMatchers("/employee/showFormForAdd").hasAnyAuthority("admin","TeamLead").
                antMatchers("/employee/addReview").hasAnyAuthority("admin","TeamLead").
                antMatchers("/employee/saveReview").hasAnyAuthority("admin","TeamLead").
                antMatchers("/employee/checkReview").hasAnyAuthority("admin","TeamLead","TeamMember").
                antMatchers("/employee/save").hasAnyAuthority("admin").
                antMatchers("/employee/save1").hasAnyAuthority("admin","TeamLead").
                antMatchers("/employee/delete").hasAnyAuthority("admin","TeamLead").
                antMatchers("/employee/home").hasAnyAuthority("admin","TeamLead","TeamMember").
                antMatchers("employee/delete?employeeId=").hasAnyAuthority("admin").
                and().

                formLogin().
                loginPage("/login").loginProcessingUrl("/authenticateTheUser").defaultSuccessUrl("/employee/home",true).and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                .logoutSuccessUrl("/login?logout").permitAll();

             /*http.authorizeRequests()
                .antMatchers("/employee/search").hasRole("admin")
                        .and()
                        .formLogin()
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll().and().exceptionHandling().accessDeniedPage("/acess-denied")
                        ;*/

    }
}
