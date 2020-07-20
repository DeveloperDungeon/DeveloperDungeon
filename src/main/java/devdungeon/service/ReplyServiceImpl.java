package devdungeon.service;

import devdungeon.domain.ReplyPageVO;
import devdungeon.domain.ReplyVO;
import devdungeon.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyMapper replyMapper;
    private final UserService userService;

    @Override
    public int register(ReplyVO replyVO) {
        return replyMapper.insert(replyVO);
    }

    @Override
    public ReplyPageVO getListWithPage(int questId, int limit, int offset) {
        return new ReplyPageVO(replyMapper.selectByQuestWithPaging(questId, limit, offset)
                .stream()
                .map(this::setAuthorDetails)
                .collect(Collectors.toList()), replyMapper.getTotalNum(questId));
    }

    @Override
    public ReplyVO getReply(int id) {
        return replyMapper.selectOne(id);
    }

    @Override
    public int remove(int id) {
        return replyMapper.delete(id);
    }

    @Override
    public int modify(ReplyVO vo) {
        return replyMapper.update(vo);
    }

    private ReplyVO setAuthorDetails(ReplyVO replyVO) {
        replyVO.setAuthorDetails(userService.getUser(replyVO.getAuthor()));
        return replyVO;
    }

}
