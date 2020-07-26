package devdungeon.interceptor;

import devdungeon.annotation.AuthAnnotation;
import devdungeon.template.RedirectBody;
import devdungeon.exception.RedirectException;
import devdungeon.service.CommentService;
import devdungeon.service.QuestService;
import devdungeon.template.ResponseTemplate;
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
    private final CommentService commentService;

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
                        throw new RedirectException(new ResponseTemplate<>(ResponseTemplate.Code.REDIRECT,
                                new RedirectBody("unAuthorized", "quest/" + id + "?redirect=unauthorized")));
                    }
                } else if (str[1].equals("comment")) {
                    if (!user.equals(commentService.getReply(id).getAuthor())) {
                        throw new RedirectException(new ResponseTemplate<>(ResponseTemplate.Code.UNAUTHORIZED,
                                new RedirectBody("unAuthorized", "")));
                    }
                }
            }
        }
        return super.preHandle(request, response, handler);
    }
}
