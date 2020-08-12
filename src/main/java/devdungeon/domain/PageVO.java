package devdungeon.domain;

import lombok.Data;

@Data
public class PageVO {
    public static final int PER_PAGE = 10;
    public static final int COUNT = 10;

    private int offset;
    private int currentPage;

    private int total;
    private int minPage;
    private int maxPage;
    private boolean hasPrev, hasNext;

    public PageVO(int currentPage, int total) {
        this.currentPage = currentPage;
        this.offset = PER_PAGE * (this.currentPage - 1);

        this.total = total;

        this.maxPage = (int) Math.ceil(currentPage / (double) COUNT) * COUNT;
        this.minPage = maxPage - (COUNT - 1);

        int realEndPage = (int) Math.ceil((double) total / PER_PAGE);

        this.maxPage = Math.min(maxPage, realEndPage);

        this.hasPrev = this.currentPage > COUNT;
        this.hasNext = this.maxPage != realEndPage;
    }
}
