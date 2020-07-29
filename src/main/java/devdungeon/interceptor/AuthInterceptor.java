package devdungeon.interceptor;

import devdungeon.annotation.AuthAnnotation;
import devdungeon.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@RequiredArgsConstructor
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private final QuestService questService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {

            HandlerMethod method = (HandlerMethod) handler;

            if (method.hasMethodAnnotation(AuthAnnotation.class)) {
                String user = (String) request.getSession().getAttribute("user");
                String[] str = request.getServletPath().split("/");

                int id = Integer.parseInt(str[str.length - 1]);

                if (str[1].equals("quest")) {
                    if (!user.equals(questService.getOne(id).getAuthor())) {
                        response.sendRedirect("/quest/" + id + "?redirect=unauthorized");
                        return false;
                    }
                }
            }
        }
        return super.preHandle(request, response, handler);
    }
}
