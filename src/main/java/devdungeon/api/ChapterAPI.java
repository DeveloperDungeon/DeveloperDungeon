package devdungeon.api;

import devdungeon.api.annotation.ApiCertifyAnnotation;
import devdungeon.domain.ChapterVO;
import devdungeon.service.ChapterService;
import devdungeon.template.RedirectTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chapter")
public class ChapterAPI {

    private final ChapterService chapterService;
    private final HttpSession session;

    @PostMapping
    @ApiCertifyAnnotation
    public ResponseEntity<RedirectTemplate> postChapterWrite(@RequestBody ChapterVO chapterVO, @RequestBody boolean isPublic) {
        chapterService.addChapter(chapterVO, isPublic, (String) session.getAttribute("user"));
        return new ResponseEntity<>(new RedirectTemplate("/chapter"), HttpStatus.MULTIPLE_CHOICES);
    }

    @GetMapping("/writable")
    @ApiCertifyAnnotation
    public ResponseEntity<List<ChapterVO>> getWritableChapter() {
        String userId = (String) session.getAttribute("user");
        List<ChapterVO> writableChapterList = chapterService.findChapters(userId);
        return new ResponseEntity<>(writableChapterList, HttpStatus.OK);
    }
}
