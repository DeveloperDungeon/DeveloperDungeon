package devdungeon.service;

import devdungeon.domain.UserVO;
import devdungeon.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper = null;

    @Override
    public List<UserVO> getAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public UserVO getUser(String id) {
        return userMapper.selectOne(id);
    }

    @Override
    public boolean findUser(String id) {
        UserVO user = getUser(id);
        if( user == null) {
            return false;
        }
        return true;
    }

    @Override
    public int addUser(UserVO user) {
        return userMapper.insertUser(user);
    }
}
