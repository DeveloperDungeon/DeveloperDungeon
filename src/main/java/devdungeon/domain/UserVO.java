package devdungeon.domain;

import lombok.Data;

@Data
public class UserVO {
    String id;
    String password;
    String nickName;
    String email;
}
