package devdungeon.service;

import devdungeon.domain.QuestVO;
import devdungeon.domain.UserVO;
import devdungeon.mapper.SearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final SearchMapper sm;
    private final UserService us;

    @Override
    public List<QuestVO> searchByAuthor(String text) {
        return sm.selectByAuthor("%" + text + "%").stream().map(this::setAuthorDetails).collect(Collectors.toList());
    }

    @Override
    public List<QuestVO> searchByContent(String text) {
        return sm.selectByContent("%" + text + "%").stream().map(this::setAuthorDetails).collect(Collectors.toList());
    }

    @Override
    public List<QuestVO> searchByTitle(String text) {
        return sm.selectByTitle("%" + text + "%").stream().map(this::setAuthorDetails).collect(Collectors.toList());
    }

    @Override
    public List<QuestVO> searchByTitleContent(String text) {
        return sm.selectByTitleContent("%" + text + "%").stream().map(this::setAuthorDetails).collect(Collectors.toList());
    }

    public QuestVO setAuthorDetails(QuestVO questVO) {
        UserVO user = us.getUser(questVO.getAuthor());

        questVO.setAuthorDetails(user);

        return questVO;
    }
}
