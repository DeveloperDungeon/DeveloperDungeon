package devdungeon.controller;


import devdungeon.domain.UserVO;
import devdungeon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "prevUrl", required = false, defaultValue = "") String prevUrl) {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@RequestBody UserVO user, HttpServletRequest request) {
        if (userService.findUser(user.getId(), user.getPassword())) {
            request.getSession().setAttribute("user", user.getId());
            String prevUrl = request.getHeader("referer");
            String redUrl = "redirect:/";
            int idx = prevUrl.indexOf("=");
            if(idx != -1){
                redUrl+=prevUrl.substring(idx+1);
            }
            return redUrl;
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong user id or password");

    }
}
