package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

    private int invoiceNumber;

    private PersonDTO seller;

    private PersonDTO buyer;

    @JsonFormat(locale = "yyyy-mm-dd")
    private Date issued;

    @JsonFormat(locale = "yyyy-mm-dd")
    private Date dueDate;

    private String product;

    private Long price;

    private int vat;

    private String note;

    @JsonProperty("_id")
    private long id;


}
