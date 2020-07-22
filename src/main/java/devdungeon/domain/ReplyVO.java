package devdungeon.domain;

import lombok.Data;

@Data
public class ReplyVO {
    private int id;
    private int questId;
    private String author;
    private String content;
    private long regTime;

    private UserVO authorDetails;
}
