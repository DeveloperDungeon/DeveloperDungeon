package devdungeon.controller;

import devdungeon.controller.annotation.AuthAnnotation;
import devdungeon.controller.annotation.CertifyAnnotation;
import devdungeon.domain.PageVO;
import devdungeon.service.ChapterService;
import devdungeon.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/quest")
public class QuestController {

    private final QuestService questService;
    private final ChapterService chapterService;
    private final HttpSession session;

    @GetMapping
    public String getQuestList(Model model, @RequestParam(value = "page", required = false,
            defaultValue = "1") int page) {

        PageVO pageVO = new PageVO(page, questService.getTotalQuestNum());
        model.addAttribute("pageInfo", pageVO);
        model.addAttribute("questList",
                questService.getQuestWithPage(PageVO.PER_PAGE, pageVO.getOffset()));
        return "quest/list";
    }

    @GetMapping("/{id}")
    public String getQuest(Model model, @PathVariable("id") Integer id, @RequestParam(value="c",required = false) int chapterId) {
        model.addAttribute("quest", questService.getOne(id));
        model.addAttribute("chapterId",chapterService.findChapter(chapterId).getId());
        return "quest/view";
    }

    @GetMapping("/write")
    @CertifyAnnotation
    public String getQuestWrite(Model model) {
        String curAuthor = (String) session.getAttribute("user");
        model.addAttribute("chapterList", chapterService.findWritableChapters(curAuthor));
        return "quest/write";
    }

    @GetMapping("/edit/{id}")
    @AuthAnnotation
    public String getQuestEdit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("quest", questService.getOne(id));
        model.addAttribute("type", "edit");
        return "quest/write";
    }
}
