package devdungeon.template;

import lombok.Data;

@Data
public class RedirectTemplate {
    private String url;

    public RedirectTemplate(String url) {
        this.url = url;
    }
}
