package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Information to invoice
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

    @JsonProperty("_id")
    private long id;

    private int invoiceNumber;

    private PersonDTO seller;

    private PersonDTO buyer;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date issued;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date dueDate;

    private String product;

    private Long price;

    private int vat;

    private String note;




}
