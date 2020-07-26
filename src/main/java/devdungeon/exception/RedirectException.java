package devdungeon.exception;

import devdungeon.domain.ResponseVO;
import devdungeon.templete.Response;

public class RedirectException extends Exception {
    private final Response<ResponseVO> response;

    public RedirectException(Response<ResponseVO> response) {
        this.response = response;
    }
}
