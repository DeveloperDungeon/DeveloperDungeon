package devdungeon.interceptor;

import devdungeon.annotation.ApiCertifyAnnotation;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class ApiLoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            String user = (String) request.getSession().getAttribute("user");
            if (method.hasMethodAnnotation(ApiCertifyAnnotation.class) && user == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "/login");
            }
        }
        return super.preHandle(request, response, handler);
    }
}
