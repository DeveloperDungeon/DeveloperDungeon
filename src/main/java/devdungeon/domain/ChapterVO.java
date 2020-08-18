package devdungeon.domain;

import lombok.Data;

import java.util.List;

@Data
public class ChapterVO {
    private int id;
    private String title;
    private String description;

    private List<QuestVO> quests;
    private List<String> users;
}
