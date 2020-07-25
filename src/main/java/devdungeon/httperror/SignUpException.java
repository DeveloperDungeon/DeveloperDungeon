package devdungeon.httperror;

public class SignUpException extends Exception {

    private final Integer ErrCode;
    private final String ErrMsg;

    public SignUpException(Integer ErrCode, String ErrMsg) {
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
