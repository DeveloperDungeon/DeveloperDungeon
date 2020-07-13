package devdungeon.service;

import devdungeon.domain.UserVO;

import java.util.List;

public interface UserService {
    public List<UserVO> getAllUser();

    public UserVO getUser(String id);

    public int getUser(String id,String password);

    public boolean findUser(String id);

    public boolean findUser(String id,String password);

    public int addUser(UserVO user);
    


}
