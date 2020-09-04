package devdungeon.controller.interceptor;

import devdungeon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Configuration
public class ConversionInterceptor extends HandlerInterceptorAdapter {
    private final UserService userService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (handler instanceof HandlerMethod && modelAndView != null) {
            String id = (String) request.getSession().getAttribute("user");
            String nickname = null;

            if (id != null) nickname = userService.getUser(id).getNickName();

            modelAndView.addObject("nickname", nickname);
            modelAndView.addObject("id", id);
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}
