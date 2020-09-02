package devdungeon.service;

import devdungeon.domain.ChapterVO;
import devdungeon.mapper.ChapterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private final ChapterMapper chapterMapper;

    /**
     * count : DB에 성공적으로 작업이 수행되었는지를 나타내는 리턴값
     *
     * 추후에 transation을 이용해서 하나의 논리단위화 해주어야한다.
     * chapter만 인서트되고 화이트리스트는 저장이 안되는 경우를 대비해서.
     * */
    @Override
    public int addChapter(ChapterVO chapterVO, String userId) {
        int count = chapterMapper.insertChapter(chapterVO);
        if (chapterVO.getIsPublic() == 0) chapterMapper.insertWhitelist(Collections.singletonList(userId)
                ,chapterVO.getId());
        return count;
    }

    @Override
    public List<ChapterVO> getWritableChapters(String userId) {
        List<ChapterVO> writableChapters = new ArrayList<>();

        writableChapters.addAll(chapterMapper.selectPrivateWritableChapter(userId));
        writableChapters.addAll(chapterMapper.selectPublicWritableChapter());
        return writableChapters;
    }

    @Override
    public ChapterVO getChapter(int id) {
        return chapterMapper.selectChapter(id);
    }

    @Override
    public int removeChapter(int id) {
        return chapterMapper.removeChapter(id);
    }
}
