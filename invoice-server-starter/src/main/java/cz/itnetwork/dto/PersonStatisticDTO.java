package cz.itnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data for individual statistic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonStatisticDTO {

    private long personId;

    private String personName;

    private long revenue;
}
