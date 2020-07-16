package devdungeon.domain;

import lombok.Data;

@Data
public class PageDTO {
    private int limit;
    private int offset;
    private int currentPage;

    private int total;
    private int minPage;
    private int maxPage;
    private boolean hasPrev, hasNext;

    public PageDTO(int currentPage, int total) {
        this.currentPage = currentPage;
        this.limit = 10;
        this.offset = this.limit * (this.currentPage - 1);

        this.total = total;

        this.maxPage = (int) Math.ceil(currentPage / 10.0) * 10;
        this.minPage = maxPage - 9;

        int realEndPage = (int) Math.ceil((total * 1.0) / limit);

        this.maxPage = maxPage < realEndPage ? this.maxPage : realEndPage;

        this.hasPrev = this.currentPage > 10;
        this.hasNext = this.maxPage != realEndPage;
    }
}
