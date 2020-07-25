package devdungeon.controller;


import devdungeon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "prevUrl", required = false, defaultValue = "") String prevUrl) {
        return "login";
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postLogin(@RequestParam Map<String, String> paramMap, HttpServletRequest request, Model model) {
        if (paramMap == null || !paramMap.containsKey("id") || !paramMap.containsKey("password")) {
            model.addAttribute("errorMessage","아이디와 비밀번호를 모.두. 입력해주세요.");
            return "login";
        }

        String id = paramMap.get("id");
        String password = paramMap.get("password");

        System.out.println("Login request!");
        if (userService.findUser(id, password)) {
            request.getSession().setAttribute("user", id);
            String prevUrl = request.getHeader("referer");
            String redUrl = "redirect:/";
            int idx = prevUrl.indexOf("=");
            if (idx != -1) {
                redUrl += prevUrl.substring(idx + 1);
            }
            return redUrl;
        }
        model.addAttribute("errorMessage","아이디 혹은 비밀번호가 틀렸습니다.");
        return "login";
    }
}
