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

import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam(value = "prevUrl",required = false, defaultValue = "") String prevUrl,
                            @RequestBody UserVO user, HttpSession session) {
        if (userService.findUser(user.getId(), user.getPassword())) {
            session.setAttribute("user", user.getId());

            return "redirect:/"+prevUrl;
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong user id or password");

    }
}
