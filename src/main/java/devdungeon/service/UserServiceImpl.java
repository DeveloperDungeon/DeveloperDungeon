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
        return getUser(id) != null;
    }

    @Override
    public boolean findUser(String id, String password) {
        return getUser(id, password) != 0;
    }

    @Override
    public int addUser(UserVO user) {
        return userMapper.insertUser(user);
    }

    @Override
    public boolean findUserByEmail(String email) {
        return userMapper.selectByEmail(email) != null;
    }

    @Override
    public boolean findUserByNick(String nickName) {
        return userMapper.selectByNick(nickName) != null;
    }
}
