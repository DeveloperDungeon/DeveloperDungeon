package devdungeon.interceptor;

import devdungeon.annotation.CertifyAnnotation;
import devdungeon.template.RedirectBody;
import devdungeon.exception.RedirectException;
import devdungeon.template.ResponseTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            String user = (String) request.getSession().getAttribute("user");
            if (method.hasMethodAnnotation(CertifyAnnotation.class) && user == null) {
                String redUrl = "login?prevUrl=" + request.getServletPath().substring(1);
                throw new RedirectException(new ResponseTemplate<>(ResponseTemplate.Code.REDIRECT,
                        new RedirectBody("login please", redUrl)));
            }
        }
        return super.preHandle(request, response, handler);
    }
}
