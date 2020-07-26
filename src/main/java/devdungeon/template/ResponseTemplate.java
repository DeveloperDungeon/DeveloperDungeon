package devdungeon.template;

import lombok.Data;

@Data
public class ResponseTemplate<T> {
    public static final class Code {
        public static final int OK = 200;
        public static final int REDIRECT = 300;
        public static final int BAD_REQUEST = 400;
        public static final int UNAUTHORIZED = 401;
        public static final int FORBIDDEN = 403;
        public static final int NOT_FOUND = 404;
        public static final int METHOD_NOT_ALLOWED = 405;
        public static final int INTERNAL_SERVER_ERROR = 500;
    }

    private int code;
    private T body;

    public ResponseTemplate(int code, T body) {
        this.code = code;
        this.body = body;
    }
}
