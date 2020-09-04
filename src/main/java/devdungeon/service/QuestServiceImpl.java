package devdungeon.service;

import devdungeon.domain.ChapterVO;
import devdungeon.domain.QuestVO;
import devdungeon.domain.UserVO;
import devdungeon.mapper.QuestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestServiceImpl implements QuestService {

    private final QuestMapper questMapper;
    private final UserService userService;
    private final ChapterService chapterService;

    @Override
    public List<QuestVO> getAll() {
        return questMapper.selectAll().stream()
                .map(this::setDetails)
                .collect(Collectors.toList());
    }

    @Override
    public QuestVO getOne(int id) {
        return setDetails(questMapper.selectOne(id));
    }

    @Override
    public List<QuestVO> getRecent(int amount) {
        return questMapper.selectRecent(amount).stream()
                .map(this::setDetails)
                .collect(Collectors.toList());
    }

    @Override
    public int addQuest(QuestVO questVO) {
        return questMapper.insertQuestWithChapter(questVO);
    }

    @Override
    public List<QuestVO> getQuestWithPage(int limit, int offset) {
        return questMapper.selectWithPage(limit, offset).stream()
                .map(this::setDetails)
                .collect(Collectors.toList());
    }

    @Override
    public int editQuest(QuestVO questVO) {
        return questMapper.editQuest(questVO);
    }

    @Override
    public int getTotalQuestNum() {
        return questMapper.getTotalQuestNum();
    }

    @Override
    public int remove(int id) {
        return questMapper.delete(id);
    }

    @Override
    public List<QuestVO> getUserQuestList(String author) {
        return questMapper.selectUserQuest(author).stream()
                .map(this::setDetails)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestVO> getChapterQuestList(int chapterId) {
        return questMapper.selectChapterQuest(chapterId).stream()
                .map(this::setDetails)
                .collect(Collectors.toList());
    }

    private UserVO getAuthorDetails(QuestVO questVO) {
        return userService.getUser(questVO.getAuthor());
    }

    private ChapterVO getChapterDetails(QuestVO questVO) {
        ChapterVO chapter = null;
        if (questVO.getChapterId() != null) {
            chapter = chapterService.getChapter(questVO.getChapterId());
        }
        return chapter;
    }

    private QuestVO setDetails(QuestVO questVO) {
        questVO.setChapterDetails(getChapterDetails(questVO));
        questVO.setAuthorDetails(getAuthorDetails(questVO));
        return questVO;
    }
}
