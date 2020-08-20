package devdungeon.domain;

import lombok.Data;

@Data
public class UserVO {
    private String id;
    private String password;
    private String nickName;
    private String email;
    private String role;
}
