package devdungeon.controller;

import devdungeon.annotation.CertifyAnnotation;
import devdungeon.domain.QuestVO;
import devdungeon.domain.UserVO;
import devdungeon.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/quest")
public class QuestController {

    private final QuestService questService;
    private final HttpSession session;

    @GetMapping("/list")
    public void getQuestList(Model model) {
        model.addAttribute("list",questService.getAll());
    }

    @GetMapping("/write")
    @CertifyAnnotation
    public String getQuestWrite() {
        return "quest/write";
    }

    @PostMapping("/write")
    @CertifyAnnotation
    public String postQuestWrite(@RequestBody QuestVO questVO) {
        UserVO userVO = (UserVO) session.getAttribute("user");
        questVO.setAuthor(userVO.getId());
        questService.addQuest(questVO);
        return "redirect:/quest/list";
    }

    @GetMapping("/edit")
    @CertifyAnnotation
    public String getQuestEdit(){
        return "questEdit";
    }

    @PutMapping("/edit")
    @CertifyAnnotation
    public String putQuestEdit(@RequestBody QuestVO questVO){
        questService.editQuest(questVO);
        return "questEdit";
    }
}
