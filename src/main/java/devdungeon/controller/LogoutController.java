package devdungeon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        String str = request.getHeader("referer");
        request.getSession().invalidate();
        return "redirect:/" + getPrevUrl(str);
    }

    private String getPrevUrl(String str) {
        return Arrays.stream(str.split("/"))
                .skip(3)
                .collect(Collectors.joining("/"));
    }
}
