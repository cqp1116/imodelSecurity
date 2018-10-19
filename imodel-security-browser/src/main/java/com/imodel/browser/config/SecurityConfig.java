package com.imodel.browser.config;

import com.imodel.browser.authentication.ImodelFailAuthHandler;
import com.imodel.browser.authentication.ImodelSuccessAuthHandler;
import com.imodel.core.authentication.mobile.config.SmsCodeAuthenticationConfig;
import com.imodel.core.authentication.mobile.filter.SmsCodeFilter;
import com.imodel.core.properties.SecurityProperties;
import com.imodel.core.validate.filter.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @auther
 * @create 2018/8/11
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //加载统一配置信息
    @Autowired
    private SecurityProperties securityProperties ;
    //加载成功处理
    @Autowired
    private ImodelSuccessAuthHandler modelSuccessAuthHandler;
    //加载失败处理
    @Autowired
    private ImodelFailAuthHandler modelFailAuthHandler;
    @Autowired
    private SmsCodeAuthenticationConfig smsCodeAuthenticationConfig;
    //加载密码几码加密方式
    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository =new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //第一次运行时创建
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //再加载用户名密码之前加载验证码验证的过滤器
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(modelFailAuthHandler);
        //将配置文件进行传递并调用相应的配置信息
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setAuthenticationFailureHandler(modelFailAuthHandler);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();

        http.addFilterBefore(smsCodeFilter,UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(validateCodeFilter,UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                    .loginPage("/authentication/require")
                    .loginProcessingUrl("/authentication/form")
                    .successHandler(modelSuccessAuthHandler)
                    .failureHandler(modelFailAuthHandler)
                    .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage(),"/authentication/mobile","/code/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable().apply(smsCodeAuthenticationConfig);
    }
}
