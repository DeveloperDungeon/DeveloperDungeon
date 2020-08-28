package devdungeon.controller;

import devdungeon.controller.annotation.CertifyAnnotation;
import devdungeon.service.ChapterService;
import devdungeon.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chapter")
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;
    private final QuestService questService;

    @GetMapping("/write")
    @CertifyAnnotation
    public String chapterWrite() {
        return "chapter/write";
    }

    @GetMapping("/{id}")
    public String getChapter(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("chapter", chapterService.findChapter(id));
        model.addAttribute("questList", questService.getChapterQuestList(id));
        return "chapter/view";
    }

}
