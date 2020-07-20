package devdungeon.service;

import devdungeon.domain.ReplyPageVO;
import devdungeon.domain.ReplyVO;

public interface ReplyService {
    int register(ReplyVO replyVO);

    ReplyPageVO getListWithPage(int questId, int limit, int offset);
}
