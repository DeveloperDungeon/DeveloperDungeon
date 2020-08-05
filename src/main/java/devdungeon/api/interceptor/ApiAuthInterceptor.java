package devdungeon.api.interceptor;

import devdungeon.api.annotation.ApiAuthAnnotation;
import devdungeon.service.CommentService;
import devdungeon.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@RequiredArgsConstructor
public class ApiAuthInterceptor extends HandlerInterceptorAdapter {
    private final QuestService questService;
    private final CommentService commentService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;

            if (method.hasMethodAnnotation(ApiAuthAnnotation.class)) {
                String user = (String) request.getSession().getAttribute("user");
                String[] str = request.getServletPath().split("/");

                int id = Integer.parseInt(str[str.length - 1]);

                if (str[1].equals("quest")) {
                    if (!user.equals(questService.getOne(id).getAuthor())) {
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                                "you do not have auth to access this page");
                    }
                } else if (str[1].equals("comment")) {
                    if (!user.equals(commentService.getReply(id).getAuthor())) {
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                                "you do not have auth to access this page");
                    }
                }
            }
        }

        return super.preHandle(request, response, handler);
    }
}
