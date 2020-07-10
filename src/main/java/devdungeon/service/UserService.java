package devdungeon.service;

import devdungeon.domain.UserVO;

import java.util.List;

public interface UserService {
    public List<UserVO> getAllUser();

    public UserVO getUser(String id);

    public boolean findUser(String id);
    
    public int addUser(UserVO user);
    
    public String getUserPassword(String id);
}
