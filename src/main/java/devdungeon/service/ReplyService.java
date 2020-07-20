package devdungeon.service;

import devdungeon.domain.ReplyPageVO;
import devdungeon.domain.ReplyVO;

import java.util.List;

public interface ReplyService {
    int register(ReplyVO replyVO);

    List<ReplyVO> getListWithPage(int questId, int limit, int offset);

    int getTotalNum(int questId);

    ReplyVO getReply(int id);

    int remove(int id);

    int modify(ReplyVO vo);
}
