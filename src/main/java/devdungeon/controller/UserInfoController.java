package devdungeon.controller;

import devdungeon.domain.QuestVO;
import devdungeon.service.QuestService;
import devdungeon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class UserInfoController {

    private final UserService userService;
    private final QuestService questService;
    @GetMapping("/user/{id}")
    public String getUserInfo(Model model, @PathVariable("id") Integer id){
        model.addAttribute("userInfo",userService.getUser(Integer.toString(id)));
        List<QuestVO> UserQuestList = questService.getUserQuestList(Integer.toString(id));
        model.addAttribute("questNum",UserQuestList.size());
        model.addAttribute("questList",UserQuestList);
        return "/user";
    }
}
