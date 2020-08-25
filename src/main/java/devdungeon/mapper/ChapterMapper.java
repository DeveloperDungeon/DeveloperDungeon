package devdungeon.mapper;

import devdungeon.domain.ChapterVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// Implemented in xml [devdungeon/mapper/ChapterMapper.xml]
@Mapper
public interface ChapterMapper {
    int insertChapter(ChapterVO chapterVO);

    int insertWhitelist(List<String> userIds, int chapterId);

    List<ChapterVO> selectPrivateWritableChapter(String userId);

    List<ChapterVO> selectPublicWritableChapter();

    ChapterVO selectChapter(int id);
}
