package devdungeon.controller;


import devdungeon.template.Body;
import devdungeon.template.MessageBody;
import devdungeon.template.RedirectBody;
import devdungeon.service.UserService;
import devdungeon.template.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseTemplate<Body> postLogin(@RequestParam Map<String, String> paramMap, HttpServletRequest request) {
        if (paramMap == null || !paramMap.containsKey("id") || !paramMap.containsKey("password")) {
            return new ResponseTemplate<>(ResponseTemplate.Code.BAD_REQUEST, new MessageBody("아이디와 비밀번호를 모.두. 입력해주세요."));
        }

        String id = paramMap.get("id");
        String password = paramMap.get("password");
        if (userService.findUser(id, password)) {
            request.getSession().setAttribute("user", id);
            String prevUrl = request.getHeader("referer");
            String redUrl = "/";

            if (prevUrl != null) {
                int idx = prevUrl.indexOf("=");
                if (idx != -1) {
                    redUrl += prevUrl.substring(idx + 1);
                }
            }
            return new ResponseTemplate<>(ResponseTemplate.Code.REDIRECT, new RedirectBody("success", redUrl));

        }
        return new ResponseTemplate<>(ResponseTemplate.Code.BAD_REQUEST, new MessageBody("아이디 혹은 비밀번호가 틀렸습니다."));
    }
}
