package com.example.demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomPermissionEvaluator customPermissionEvaluator;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean
    public DefaultWebSecurityExpressionHandler addWebSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(customPermissionEvaluator);
        return handler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 是否有顺序问题
        http
                .formLogin()
                //设置登录页面
                .loginPage("/login")
                //设置登录成功后跳转的地址
//                .defaultSuccessUrl("/home")
                //登录时，提交到后台的url
                .loginProcessingUrl("/user/login")
                //程序出现异常（登录，权限不足），统一处理的跳转地址
//                .failureUrl("/err")
                //自定义登录成功时的操作
                .successHandler((request, response, authentication) -> {
                    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                    System.out.println("-----> " + userDetails.getUsername());
                    System.out.println("-----> " + userDetails.getPassword());
                    response.sendRedirect("/logSuc");
                })
                // 自定义登录失败时，根据异常信息进行不同的处理
                .failureHandler((request, response, e) -> {
                    System.out.println("-----> " + e.toString());
                    response.sendRedirect("/loginFail");
                })
                .permitAll()

                .and()

                .exceptionHandling()
                //设置权限不足时，跳转的页面
                .accessDeniedPage("/err")
                //自定义权限不足时，进行的操作
//                .accessDeniedHandler((httpServletRequest, httpServletResponse, e) -> {})


                .and()

                .logout()
                //设置退出登录url
                .logoutUrl("/logout")
                //设置退出登录成功后跳转地址
//                .logoutSuccessUrl("/index")
                //自定义退出登录成功后，要进行的操作
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletRequest.getSession().invalidate();
                    httpServletResponse.sendRedirect("/index");
                })
                //设置退出后清楚session,默认为true
//                .invalidateHttpSession(true)
                .permitAll()

                .and()

                .authorizeRequests()
                .antMatchers("/", "/index", "/register", "/loginFail", "/err").permitAll()
                .anyRequest()
                .authenticated();
        http.csrf().disable();
    }
}
