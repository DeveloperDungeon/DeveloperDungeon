package devdungeon.test;

import devdungeon.domain.QuestVO;
import devdungeon.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HyunIController {
    private final QuestService questService;

    @GetMapping("/hyuni")
    public String getList(Model model) {
        List<QuestVO> quests = questService.getAll();
        model.addAttribute("questList", quests);
        return "quest/list";
    }
}
