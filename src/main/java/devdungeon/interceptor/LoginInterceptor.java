package devdungeon.interceptor;

import devdungeon.annotation.CertifyAnnotation;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            String user = (String) request.getSession().getAttribute("user");
            if (method.hasMethodAnnotation(CertifyAnnotation.class) && user == null) {

                String redUrl = "/login?prevUrl=";
                if (request.getServletPath().substring(1).equals("comment")) {
                    String[] str = request.getHeader("referer").split("/");
                    for(int i=0;i<str.length;i++){
                        System.out.println(i + " " + str[i]);
                    }
                    String prevUrl = Arrays.stream(request.getHeader("referer").split("/"))
                            .skip(3)
                            .collect(Collectors.joining("/"));
                    response.sendRedirect(redUrl + prevUrl);

                } else response.sendRedirect(redUrl + request.getServletPath().substring(1));

                return false;
            }
        }
        return super.preHandle(request, response, handler);
    }
}
