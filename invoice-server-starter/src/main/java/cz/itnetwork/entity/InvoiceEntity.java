package cz.itnetwork.entity;

import cz.itnetwork.dto.PersonDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "invoice")
@Setter
@Getter
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
