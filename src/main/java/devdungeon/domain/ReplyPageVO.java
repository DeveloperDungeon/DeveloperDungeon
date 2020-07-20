package devdungeon.domain;

import lombok.Data;

import java.util.List;

@Data
public class ReplyPageVO {
    private List<ReplyVO> list;
    int replyCnt;

    public ReplyPageVO(List<ReplyVO> list, int total) {
        this.list = list;
        this.replyCnt = total;
    }
}
