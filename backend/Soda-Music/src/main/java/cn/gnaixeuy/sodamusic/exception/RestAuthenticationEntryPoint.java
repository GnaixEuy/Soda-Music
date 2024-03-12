package cn.gnaixeuy.sodamusic.exception;

import cn.gnaixeuy.sodamusic.enums.ExceptionType;
import cn.hutool.json.JSONUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-MusicEntity
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/2/15
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ExceptionType.UNAUTHORIZED.getCode());
        errorResponse.setMsg(ExceptionType.UNAUTHORIZED.getMessage());
        response.getWriter().println(JSONUtil.parse(errorResponse));
        response.getWriter().flush();
    }
}