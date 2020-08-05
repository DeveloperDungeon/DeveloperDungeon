package devdungeon.mapper;

import devdungeon.domain.QuestVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SearchMapper {
    @Select("SELECT * FROM quest WHERE author LIKE #{text}")
    List<QuestVO> selectByAuthor(String text);

    @Select("SELECT * FROM quest WHERE content LIKE #{text}")
    List<QuestVO> selectByContent(String text);

    @Select("SELECT * FROM quest WHERE title LIKE #{text}")
    List<QuestVO> selectByTitle(String text);

    @Select("SELECT * FROM quest WHERE (title LIKE #{text} OR content LIKE #{text})")
    List<QuestVO> selectByTitleContent(String text);
}
