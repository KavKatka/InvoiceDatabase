package cz.itnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Information for general statistic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceStatisticDTO {

    private long currentYearSum;

    private long allTimeSum;

    private long invoicesCount;
}
