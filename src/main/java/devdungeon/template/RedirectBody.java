package devdungeon.template;

import lombok.Getter;

public class RedirectBody extends MessageBody {

    @Getter
    private final String redirectUrl;

    public RedirectBody(String message, String redirectUrl) {
        super(message);
        this.redirectUrl = redirectUrl;
    }
}
