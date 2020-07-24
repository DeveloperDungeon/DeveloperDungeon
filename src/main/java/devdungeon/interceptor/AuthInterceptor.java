package devdungeon.interceptor;

import devdungeon.annotation.AuthAnnotation;
import devdungeon.service.QuestService;
import devdungeon.service.ReplyService;
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
    private ReplyService replyService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {

            HandlerMethod method = (HandlerMethod) handler;

            if (method.hasMethodAnnotation(AuthAnnotation.class)) {
                String user = (String) request.getSession().getAttribute("user");
                String[] str = request.getServletPath().split("/");

                int Id = Integer.parseInt(str[str.length - 1]);

                if (str[1].equals("quest")) {
                    if (!user.equals(questService.getOne(Id).getAuthor())) {
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                                "you do not have auth to access this page");
                    }
                } else if (str[1].equals("reply")) {
                    if (!user.equals(replyService.getReply(Id).getAuthor())) {
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                                "you do not have auth to access this page");
                    }
                }
            }
        }
        return super.preHandle(request, response, handler);
    }
}
