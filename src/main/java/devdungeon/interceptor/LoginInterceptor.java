package devdungeon.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import devdungeon.annotation.CertifyAnnotation;
import devdungeon.template.RedirectBody;
import devdungeon.template.ResponseTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
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

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonStr = objectMapper.writeValueAsString(new ResponseTemplate<>(ResponseTemplate.Code.REDIRECT,
                        new RedirectBody("login please", redUrl)));

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(jsonStr);
                return false;
            }
        }
        return super.preHandle(request, response, handler);
    }
}
