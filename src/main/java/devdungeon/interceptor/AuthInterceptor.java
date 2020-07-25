package devdungeon.interceptor;

import devdungeon.annotation.AuthAnnotation;
import devdungeon.service.QuestService;
import devdungeon.service.CommentService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Setter(onMethod_ = @Autowired)
    private QuestService questService;

    @Setter(onMethod_ = @Autowired)
    private CommentService commentService;

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
                } else if (str[1].equals("comment")) {
                    if (!user.equals(commentService.getReply(id).getAuthor())) {
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                                "you do not have auth to access this page");
                    }
                }
            }
        }
        return super.preHandle(request, response, handler);
    }
}
