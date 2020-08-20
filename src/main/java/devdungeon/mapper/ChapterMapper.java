package devdungeon.mapper;

import devdungeon.domain.ChapterVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChapterMapper {
    int insertChapter(ChapterVO chapterVO);

    int insertWhitelist(List<String> userIds, int chapterId);

    @Insert("INSERT INTO whitelist(chapter_id) VALUES(#{chapterId})")
    int insertAllWhiteList(int chapterId);

    List<ChapterVO> selectWritableChapter(String userId);
}
