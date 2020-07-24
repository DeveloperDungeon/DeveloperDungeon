package devdungeon.controller;

import devdungeon.annotation.AuthAnnotation;
import devdungeon.annotation.CertifyAnnotation;
import devdungeon.domain.PageVO;
import devdungeon.domain.ReplyPageVO;
import devdungeon.domain.ReplyVO;
import devdungeon.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reply")
public class ReplyController {

    private final ReplyService replyService;
    private final HttpSession session;

    @PostMapping("/register")
    @CertifyAnnotation
    public ResponseEntity<String> postReplyRegister(@RequestBody ReplyVO replyVO) {
        replyVO.setAuthor((String) session.getAttribute("user"));
        return replyService.register(replyVO) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) :
                new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<ReplyPageVO> getListWithPage(@RequestParam("questId") int questId,
                                                       @RequestParam(value = "page", required = false, defaultValue = "1")
                                                               int page) {
        PageVO pageVO = new PageVO(page, replyService.getTotalNum(questId));

        return new ResponseEntity<>(new ReplyPageVO(replyService.getListWithPage(questId, PageVO.PER_PAGE, pageVO.getOffset()), pageVO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @AuthAnnotation
    public ResponseEntity<ReplyVO> putReplyModify(@PathVariable("id") int id) {
        return new ResponseEntity<>(replyService.getReply(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @AuthAnnotation
    public ResponseEntity<String> remove(@PathVariable("id") int id) {
        return replyService.remove(id) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) :
                new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    @AuthAnnotation
    public ResponseEntity<String> modify(@PathVariable("id") int id, @RequestBody ReplyVO replyVO) {
        replyVO.setId(id);
        return replyService.modify(replyVO) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) :
                new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
