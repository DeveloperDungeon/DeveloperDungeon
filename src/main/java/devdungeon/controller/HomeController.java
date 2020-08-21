package devdungeon.controller;

import devdungeon.domain.ChapterVO;
import devdungeon.domain.QuestVO;
import devdungeon.mapper.ChapterMapper;
import devdungeon.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final QuestService questService;

    private final ChapterMapper chapterMapper;

    private int counter = 0;


    @GetMapping("/test")
    public String test() {
        System.out.println("들어왔습니다! " + counter++);
        ChapterVO chapter = new ChapterVO();
        chapter.setTitle("새로운 챕터");
        chapter.setDescription("새로운챕터 설명해드릴게요.");
        chapterMapper.insertChapter(chapter);
        int id = chapter.getId();
        List<String> list = new ArrayList<>();
        list.add("asdf");
        list.add("1111");
        chapterMapper.insertWhitelist(list,id);
        return "index";
    }

    @GetMapping("/")
    public String welcomeToMyHouse(Model model) {
        List<QuestVO> questList = questService.getRecent(2);
        model.addAttribute("questList", questList);
        return "index";
    }
}
