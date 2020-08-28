package devdungeon.service;

import devdungeon.domain.QuestVO;

import java.util.List;

public interface QuestService {
    List<QuestVO> getAll();

    QuestVO getOne(int id);

    List<QuestVO> getRecent(int amount);

    int addQuest(QuestVO questVO);

    int editQuest(QuestVO questVO);

    int getTotalQuestNum();

    List<QuestVO> getQuestWithPage(int limit, int offset);

    int remove(int id);

    List<QuestVO> getUserQuestList(String authorId);

    List<QuestVO> getChapterQuestList(Integer chapterId);
}
