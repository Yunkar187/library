package yunkar.libary.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiKeyAuthFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String API_KEY_HEADER = "API-KEY";
    private static final String VALID_API_KEY = "dunglt";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Lấy API Key từ Header
        String apiKey = request.getHeader(API_KEY_HEADER);

        // Kiểm tra tính hợp lệ của API Key
        if (VALID_API_KEY.equals(apiKey)) {
            // Tạo danh sách quyền chứa ROLE_USER
            List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
            // Tạo đối tượng Authentication và set vào SecurityContext
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(apiKey, null,authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            // Nếu không có API Key hoặc sai key, throw exception hoặc trả lỗi 401
            logger.warn("Invalid or missing API Key");

            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("timestamp", System.currentTimeMillis());
            errorResponse.put("status", HttpStatus.UNAUTHORIZED.value());
            errorResponse.put("error", "Unauthorized");
            errorResponse.put("message", "Invalid or missing API Key");
            errorResponse.put("path", request.getRequestURI());

            // Set response
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
            response.getWriter().flush();

            return;
        }

        filterChain.doFilter(request, response);
    }

}
