package devdungeon.domain;

import lombok.Data;

@Data
public class ResponseVO {
    private String msg;
    private String redirectUrl;

    public ResponseVO(String msg, String url) {
        this.msg = msg;
        this.redirectUrl = url;
    }
}
