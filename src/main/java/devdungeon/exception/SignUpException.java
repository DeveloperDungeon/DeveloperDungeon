package devdungeon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SignUpException extends RuntimeException {

    private final Integer ErrCode;
    private final String ErrMsg;

    public SignUpException(Integer ErrCode, String ErrMsg) {
        super();
        this.ErrCode = ErrCode;
        this.ErrMsg = ErrMsg;

    }

    public Integer getErrCode() {
        return this.ErrCode;
    }

    public String getErrMsg() {
        return this.ErrMsg;
    }
}
