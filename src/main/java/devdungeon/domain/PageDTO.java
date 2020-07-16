package devdungeon.domain;

import lombok.Data;

@Data
public class PageDTO {

    private int total;
    private int minPage;
    private int maxPage;
    private boolean prev, next;

    private PageCriteria pageCriteria;

    public PageDTO(PageCriteria pageCriteria, int total) {
        this.total = total;
        this.pageCriteria = pageCriteria;


        this.maxPage = (int) Math.ceil(pageCriteria.getCurrentPage() / 10.0) * 10;
        this.minPage = maxPage - 9;

        int realEndPage = (int) Math.ceil((total * 1.0) / pageCriteria.getLimit());

        this.maxPage = maxPage < realEndPage ? this.maxPage : realEndPage;

        this.prev = this.pageCriteria.getCurrentPage() > 10;
        this.next = this.maxPage != realEndPage;
    }

}
