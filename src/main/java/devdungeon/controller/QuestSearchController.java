package devdungeon.controller;

import devdungeon.domain.QuestVO;
import devdungeon.domain.SearchVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/search")
public class QuestSearchController {

    @GetMapping
    public List<QuestVO> getQuestSearch(@RequestBody SearchVO searchVO){
        return null;
    }
}
