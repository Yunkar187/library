package yunkar.libary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // xử lý key (api)
        http
                .csrf(csrf -> csrf.disable()) // Tắt CSRF vì API Key thường dùng cho REST API không trạng thái
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Không lưu session
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/author/**").hasRole("ADMIN") // Các endpoint public không cần API key
                        .requestMatchers("/test/**").hasRole("USER")
                        .anyRequest().authenticated() // Các endpoint khác bắt buộc phải authen
                )
                // Thêm ApiKeyAuthFilter trước filter mặc định của Spring
                .addFilterBefore(new ApiKeyAuthFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
