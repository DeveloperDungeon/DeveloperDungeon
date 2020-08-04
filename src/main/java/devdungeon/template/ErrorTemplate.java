package devdungeon.template;

import lombok.Data;

@Data
public class ErrorTemplate {
    private int errorCode;

    public ErrorTemplate(int num) {
        this.errorCode = num;
    }
}
