package devdungeon.interceptor;

import devdungeon.annotation.AuthAnnotation;
import devdungeon.annotation.CertifyAnnotation;
import devdungeon.domain.UserVO;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            UserVO user = (UserVO) request.getSession().getAttribute("user");
            if (method.hasMethodAnnotation(CertifyAnnotation.class) && user == null) {
                response.sendRedirect("/login");
                return false;
            }
            /**
             * 차후에 user.getLevel을 통해 권한 등급을 부여.
             * 권한이 안되면 exception처리.
             * */
            if(method.hasMethodAnnotation(AuthAnnotation.class) && user == null) {
                throw new AuthenticationException(request.getRequestURI());
            }
        }

        return super.preHandle(request, response, handler);
    }
}
