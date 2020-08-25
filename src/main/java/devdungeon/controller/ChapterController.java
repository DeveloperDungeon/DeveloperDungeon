package devdungeon.controller;

import devdungeon.controller.annotation.CertifyAnnotation;
import devdungeon.service.ChapterService;
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

    @GetMapping("/write")
    @CertifyAnnotation
    public String chapterWrite() {
        return "chapter/write";
    }

    @GetMapping("/{id}")
    public String getChapter(Model model, @PathVariable("id") int id) {
        model.addAttribute("chapter", chapterService.findChapter(id));
        return "chapter/view";
    }

}
