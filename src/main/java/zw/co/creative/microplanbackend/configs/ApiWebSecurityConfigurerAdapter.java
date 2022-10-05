package zw.co.creative.microplanbackend.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class ApiWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).invalidSessionUrl("/login?InvalidSession");
        ((HttpSecurity)((HttpSecurity)((FormLoginConfigurer)((FormLoginConfigurer)((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((HttpSecurity)http.csrf().disable()).authorizeRequests().antMatchers(new String[]{"/", "/js/**", "/css/**", "/img/**", "/images/**", "/build/**", "/doc/**", "/vendor/**", "/src/**", "/maps/**", "/https://code.jquery.com/**", "/https://cdn.datatables.net/**", "/https://cdnjs.cloudflare.com//**", "/webjars/**"})).permitAll().antMatchers(HttpMethod.POST, new String[]{"/*"})).permitAll().antMatchers(HttpMethod.POST, new String[]{"/login"})).permitAll().antMatchers(HttpMethod.GET, new String[]{"/login"})).permitAll().antMatchers(new String[]{"/loginerror"})).permitAll().antMatchers(new String[]{"/forgetpassword"})).permitAll().antMatchers(new String[]{"/resetpassword"})).permitAll().antMatchers(new String[]{"/employees/changepassword"})).permitAll().antMatchers(new String[]{"/employees/changepasswordSave"})).permitAll()
                .antMatchers(new String[]{"/assetrecord/reception"})).permitAll().antMatchers(new String[]{"/assetrecord/reception/accept"})).permitAll().antMatchers(new String[]{"/assetrecord/reception/reject"})).permitAll().antMatchers(new String[]{"/computerDashboard/reception"})).permitAll().antMatchers(new String[]{"/computerDashboard/reception/accept"})).permitAll().antMatchers(new String[]{"/computerDashboard/reception/reject"})).permitAll().anyRequest()).authenticated().and()).formLogin().loginPage("/login").successHandler(this.myAuthenticationSuccessHandler())).failureUrl("/loginerror")).and()).logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout").permitAll().and()).exceptionHandling();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider());
    }

    @Override
    public void configure(WebSecurity web){
        web.ignoring()
                .antMatchers("resources/**","/static/**","/api/**");
    }

    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MySimpleUrlAuthenticationSuccessHandler();
    }
}
