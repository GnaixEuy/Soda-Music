package cn.gnaixeuy.sodamusic.config;

import cn.gnaixeuy.sodamusic.exception.RestAuthenticationEntryPoint;
import cn.gnaixeuy.sodamusic.filter.JwtAuthorizationFilter;
import cn.gnaixeuy.sodamusic.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-MusicEntity
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/2/15
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig {

    public static final String SECRET = "soda-music";
    public static final long EXPIRATION_TIME = 86400000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String[] PASS_URL = {"/auth/**", "/swagger**/**", "/v3/**", "/doc.html", "/druid/**"};
    private AuthService authService;
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                // 下面开始设置权限

                .authorizeRequests(authorize -> authorize
                        // 请求放开
                        .antMatchers(
                                SecurityConfig.PASS_URL
                        ).permitAll()
                        //暂时开放
                        // 其他地址的访问均需验证权限
                        .antMatchers("/druid/**").permitAll()
                        .anyRequest()
                        .authenticated()
                )

                .headers()
                .frameOptions()
                .disable()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().frameOptions().disable()
                .and()
                .cors()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(this.restAuthenticationEntryPoint)
                .and()
                // 添加 JWT 过滤器，JWT 过滤器在用户名密码认证过滤器之前
                .addFilterBefore(new JwtAuthorizationFilter(this.authenticationManager(), this.authService), UsernamePasswordAuthenticationFilter.class)
                // 认证用户时用户信息加载配置，注入springAuthUserService
                .userDetailsService(this.authService)
                .build();
    }

    /**
     * 配置跨源访问(CORS)
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }


    /**
     * 密码明文加密方式配置
     *
     * @return BCryptPasswordEncoder密码加密编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     *
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setRestAuthenticationEntryPoint(RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    @Autowired
    public void setAuthenticationConfiguration(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

}