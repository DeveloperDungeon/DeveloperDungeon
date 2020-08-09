package devdungeon.controller.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class ConversionInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;

            String user = (String) request.getSession().getAttribute("user");
            if (user == null) modelAndView.addObject("isLogin", false);
            else modelAndView.addObject("isLogin", true);
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}
