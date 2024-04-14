package cz.itnetwork.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Entity represent invoice
 */
@Entity(name = "invoice")
@Setter
@Getter
public class InvoiceEntity {
    /**
     * Generated id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * Invoice number
     */
    @Column
    private int invoiceNumber;
    /**
     * Date issued
     */
    @Column
    private Date issued;
    /**
     * Date due date
     */
    @Column
    private Date dueDate;
    /**
     * Product
     */
    @Column
    private String product;
    /**
     * Price
     */
    @Column
    private Long price;
    /**
     * Vat
     */
    @Column
    private int vat;
    /**
     * Note
     */
    @Column
    private String note;
    /**
     * Specific person - buyer
     */
    @ManyToOne
    private PersonEntity buyer;
    /**
     * Specific person - seller
     */
    @ManyToOne
    private PersonEntity seller;
}
