package devdungeon.mapper;

import devdungeon.domain.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user ")
    List<UserVO> selectAll();

    @Select("SELECT * FROM user WHERE id=#{id}")
    UserVO selectOne(String id);

    @Select("SELECT * FROM user WHERE id=#{id} AND password=#{password}")
    UserVO selectOne(String id,String password);

    @Insert("INSERT INTO user values(#{id},#{password},#{nickName},#{email})")
    int insertUser(UserVO user);


}
