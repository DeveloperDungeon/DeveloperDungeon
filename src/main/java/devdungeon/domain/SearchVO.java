package devdungeon.domain;

import lombok.Data;

@Data
public class SearchVO {
    private String method;
    private String text;
    public SearchVO(String method, String text){
        this.method = method;
        this.text = text;
    }

}
