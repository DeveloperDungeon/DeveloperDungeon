package devdungeon.exception;

import devdungeon.template.RedirectBody;
import devdungeon.template.ResponseTemplate;

public class RedirectException extends Exception {
    private final ResponseTemplate<RedirectBody> responseTemplate;

    public RedirectException(ResponseTemplate<RedirectBody> responseTemplate) {
        this.responseTemplate = responseTemplate;
    }
}
