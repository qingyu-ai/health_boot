package com.qy.config;

import com.qy.pojo.Permission;
import com.qy.pojo.Role;
import com.qy.pojo.User;
import com.qy.service.IUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Security配置类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 使用dubbo通过网络远程调用服务提供方获取数据库中的用户信息
    @DubboReference
    private IUserService userService;

    // 密码加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        super.configure(webSecurity);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // 关闭csrf
        httpSecurity.csrf().disable();
        // 放行与认证
        httpSecurity.authorizeRequests()
                .antMatchers("/login.html",
                        "/css/**",
                        "/img/**",
                        "/js/**",
                        "/plugins/**",
                        "template",
                        "/favicon.ico",
                        "/doc.html",
                        "/webjars/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v2/api-docs/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/pages/main.html");
        //让X-frame-options失效,去除iframe限制
        httpSecurity.headers().frameOptions().disable();

    }

    // 自定义登录逻辑
    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return username -> {
                User user = userService.findByUsername(username);
                if (user == null) {
                    // 用户名不存在
                    return null;
                }

                List<GrantedAuthority> list = new ArrayList<>();

                // 动态为当前用户授权
                Set<Role> roles = user.getRoles();
                roles.forEach(role -> {
                    // 遍历角色集合，为用户授予角色
                    list.add(new SimpleGrantedAuthority(role.getKeyword()));
                    Set<Permission> permissions = role.getPermissions();
                    permissions.forEach(permission -> {
                        // 遍历集合为用户授权
                        list.add(new SimpleGrantedAuthority(permission.getKeyword()));
                    });
                });

            return new org.springframework.security.core.userdetails.User(username, user.getPassword(), list);
        };
    }

}
