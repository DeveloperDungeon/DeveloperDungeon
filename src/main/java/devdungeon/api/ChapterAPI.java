package devdungeon.api;

import devdungeon.api.annotation.ApiCertifyAnnotation;
import devdungeon.domain.ChapterVO;
import devdungeon.service.ChapterService;
import devdungeon.template.RedirectTemplate;
import lombok.Data;
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
    public ResponseEntity<RedirectTemplate> postChapterWrite(@RequestBody ChapterWriteRequestBody requestBody) {
        ChapterVO newChapter = new ChapterVO();
        newChapter.setTitle(requestBody.title);
        newChapter.setDescription(requestBody.description);

        chapterService.addChapter(newChapter, requestBody.isPublic == 1, (String) session.getAttribute("user"));
        return new ResponseEntity<>(new RedirectTemplate("/chapter"), HttpStatus.MULTIPLE_CHOICES);
    }

    @GetMapping
    @ApiCertifyAnnotation
    public ResponseEntity<List<ChapterVO>> getWritableChapter(@RequestParam(value = "writeable", required=false, defaultValue = "false")
                                                                          boolean writeable) {
        if(writeable) {
            String userId = (String) session.getAttribute("user");
            List<ChapterVO> chapterList = chapterService.findChapters(userId);
            return new ResponseEntity<>(chapterList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @Data
    static class ChapterWriteRequestBody {
        private String title;
        private String description;
        private int isPublic;
    }
}
