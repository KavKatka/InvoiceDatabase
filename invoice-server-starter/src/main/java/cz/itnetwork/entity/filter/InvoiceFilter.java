package cz.itnetwork.entity.filter;

import lombok.Data;

/**
 * Parameters for filters
 */
@Data
public class InvoiceFilter {

    private Long buyerID;

    private Long sellerID;

    private String product;

    private Long minPrice;

    private Long maxPrice;

    private int limit = 3;
}
