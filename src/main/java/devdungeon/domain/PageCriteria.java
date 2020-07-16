package devdungeon.domain;

import lombok.Data;

@Data
public class PageCriteria {
    private int limit;
    private int offset;
    private int currentPage;

    public PageCriteria() {
        this.currentPage = 1;
        this.limit = 10;
        createOffset();
    }

    public PageCriteria(int currentPage) {
        this.currentPage = currentPage;
        this.limit = 10;
        createOffset();
    }

    public PageCriteria(int currentPage, int limit) {
        this.currentPage = currentPage;
        this.limit = limit;
        createOffset();
    }

    private void createOffset() {
        this.offset = this.limit * (this.currentPage - 1);
    }
}
