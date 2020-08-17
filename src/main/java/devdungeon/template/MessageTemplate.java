package devdungeon.template;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessageTemplate extends Body {

    @Getter
    private final String message;
}
