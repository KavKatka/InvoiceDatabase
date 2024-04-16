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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int invoiceNumber;

    @Column
    private Date issued;

    @Column
    private Date dueDate;

    @Column
    private String product;

    @Column
    private Long price;

    @Column
    private int vat;

    @Column
    private String note;

    @ManyToOne
    private PersonEntity buyer;

    @ManyToOne
    private PersonEntity seller;
}
