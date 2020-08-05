package devdungeon.mapper;

import devdungeon.domain.CommentVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("INSERT INTO comment(quest_id, author, content, reg_time) VALUES(#{questId}, #{author}, #{content}, #{regTime})")
    int insert(CommentVO commentVO);

    @Select("SELECT * FROM comment WHERE quest_id=#{questId} ORDER BY reg_time DESC" +
            " LIMIT #{limit, jdbcType=INTEGER} OFFSET #{offset, jdbcType=INTEGER}")
    List<CommentVO> selectByQuestWithPaging(int questId, int limit, int offset);

    @Select("SELECT COUNT(*) FROM comment WHERE quest_id=#{questId}")
    int getTotalNum(int questId);

    @Delete("DELETE FROM comment WHERE id=#{id}")
    int delete(int id);

    @Select("SELECT * FROM comment WHERE id=#{id}")
    CommentVO selectOne(int id);

    @Update("UPDATE comment SET content=#{content} WHERE id=#{id}")
    int update(CommentVO vo);
}
