package devdungeon.controller;

import devdungeon.controller.annotation.CertifyAnnotation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chapter")
@RequiredArgsConstructor
public class ChapterController {

    @GetMapping("/write")
    @CertifyAnnotation
    public String chapterWrite() {
        return "chapter/write";
    }
}
