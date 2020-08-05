package devdungeon.template;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessageBody extends Body {

    @Getter
    private final String message;
}
