package devdungeon.api;

import devdungeon.domain.UserVO;
import devdungeon.service.UserService;
import devdungeon.template.ErrorTemplate;
import devdungeon.template.RedirectTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginAPI {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Object> postLogin(@RequestBody UserVO userVO, HttpServletRequest request) {
        if (userVO.getId() == null || userVO.getPassword() == null) {
            return new ResponseEntity<>(new ErrorTemplate(1), HttpStatus.BAD_REQUEST);
        }

        String id = userVO.getId();
        String password = userVO.getPassword();

        System.out.println("Login request!");
        if (userService.findUser(id, password)) {
            request.getSession().setAttribute("user", id);
            String prevUrl = request.getHeader("referer");
            String redUrl = "/";
            int idx = prevUrl.indexOf("=");
            if (idx != -1) {
                redUrl += prevUrl.substring(idx + 1);
            }
            return new ResponseEntity<>(new RedirectTemplate(redUrl), HttpStatus.MULTIPLE_CHOICES);
        }

        return new ResponseEntity<>(new ErrorTemplate(2), HttpStatus.BAD_REQUEST);
    }
}
