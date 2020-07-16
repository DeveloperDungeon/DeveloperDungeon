package devdungeon.service;

import devdungeon.domain.UserVO;
import devdungeon.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public List<UserVO> getAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public UserVO getUser(String id) {
        return userMapper.selectOne(id);
    }

    @Override
    public int getUser(String id, String password) {
        return userMapper.loginCheck(id, password);
    }

    @Override
    public boolean findUser(String id) {
        UserVO user = getUser(id);
        if (user == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean findUser(String id, String password) {
        int userNum = getUser(id, password);
        if (userNum == 0) {
            return false;
        }
        return true;
    }

    @Override
    public int addUser(UserVO user) {
        return userMapper.insertUser(user);
    }

    @Override
    public boolean findUserByEmail(String email){
        if(userMapper.selectByEmail(email)==null)
            return true;
        return false;
    }

    @Override
    public boolean findUserByNick(String nickName){
        if(userMapper.selectByNick(nickName)==null)
            return true;
        return false;
    }
}
