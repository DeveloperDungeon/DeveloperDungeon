package devdungeon.mapper;

import devdungeon.domain.QuestVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestMapper {
    @Select("SELECT * FROM quest")
    List<QuestVO> selectAll();

    @Select("SELECT * FROM quest WHERE id=#{id}")
    QuestVO selectOne(int id);

    @Select("SELECT * FROM quest ORDER BY reg_time DESC LIMIT #{amount}")
    List<QuestVO> selectRecent(int amount);

    @Insert("INSERT INTO quest(title, content, author, reg_time, chapter_id) VALUES(#{title}, #{content}, #{author}, #{regTime}, #{chapterId})")
    int insertQuestWithChapter(QuestVO questVO);

    @Update("UPDATE quest SET title=#{title}, content=#{content} WHERE id=#{id}")
    int editQuest(QuestVO questVO);

    @Select("SELECT COUNT(*) FROM quest WHERE id > 0")
    int getTotalQuestNum();

    @Select("SELECT * FROM quest ORDER BY reg_time DESC" +
            " LIMIT #{limit, jdbcType=INTEGER} OFFSET #{offset, jdbcType=INTEGER}")
    List<QuestVO> selectWithPage(int limit, int offset);

    @Delete("DELETE FROM quest WHERE id=#{id}")
    int delete(int id);

    @Select("SELECT * FROM quest WHERE author=#{authorId}")
    List<QuestVO> selectUserQuest(String authorId);

    @Select("SELECT * FROM quest WHERE chapter_id=#{chapterId}")
    List<QuestVO> selectChapterQuest(int chapterId);
}
