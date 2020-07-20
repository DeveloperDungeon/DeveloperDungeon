package devdungeon.domain;

import lombok.Data;

@Data
public class PageVO {
    public static final int QUESTS_PER_PAGE = 10;
    public static final int PAGE_COUNT = 10;

    private int offset;
    private int currentPage;

    private int total;
    private int minPage;
    private int maxPage;
    private boolean hasPrev, hasNext;

    public PageVO(int currentPage, int total) {
        this.currentPage = currentPage;
        this.offset = QUESTS_PER_PAGE * (this.currentPage - 1);

        this.total = total;

        this.maxPage = (int) Math.ceil(currentPage / (double) PAGE_COUNT) * PAGE_COUNT;
        this.minPage = maxPage - (PAGE_COUNT - 1);

        int realEndPage = (int) Math.ceil((double) total / QUESTS_PER_PAGE);

        this.maxPage = Math.min(maxPage, realEndPage);

        this.hasPrev = this.currentPage > PAGE_COUNT;
        this.hasNext = this.maxPage != realEndPage;
    }
}
