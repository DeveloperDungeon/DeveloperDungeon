package devdungeon.domain;

import lombok.Data;

@Data
public class LoginVO {
    String id;
    String password;
    public String getId() {
    	return this.id;
    }
    public String getPassword() {
    	return this.password;
    }
}

