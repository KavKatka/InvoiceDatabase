/*  _____ _______         _                      _
 * |_   _|__   __|       | |                    | |
 *   | |    | |_ __   ___| |___      _____  _ __| | __  ___ ____
 *   | |    | | '_ \ / _ \ __\ \ /\ / / _ \| '__| |/ / / __|_  /
 *  _| |_   | | | | |  __/ |_ \ V  V / (_) | |  |   < | (__ / /
 * |_____|  |_|_| |_|\___|\__| \_/\_/ \___/|_|  |_|\_(_)___/___|
 *                                _
 *              ___ ___ ___ _____|_|_ _ _____
 *             | . |  _| -_|     | | | |     |  LICENCE
 *             |  _|_| |___|_|_|_|_|___|_|_|_|
 *             |_|
 *
 *   PROGRAMOVÁNÍ  <>  DESIGN  <>  PRÁCE/PODNIKÁNÍ  <>  HW A SW
 *
 * Tento zdrojový kód je součástí výukových seriálů na
 * IT sociální síti WWW.ITNETWORK.CZ
 *
 * Kód spadá pod licenci prémiového obsahu a vznikl díky podpoře
 * našich členů. Je určen pouze pro osobní užití a nesmí být šířen.
 * Více informací na http://www.itnetwork.cz/licence
 */
package cz.itnetwork.entity;

import cz.itnetwork.constant.Countries;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Entity represent specific person
 */
@Entity(name = "person")
@Getter
@Setter
public class PersonEntity {
    /**
     * Generated id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Full name
     */
    @Column(nullable = false)
    private String name;
    /**
     * Identification number
     */
    @Column(nullable = false)
    private String identificationNumber;
    /**
     * Tax number
     */
    private String taxNumber;
    /**
     * Account number
     */
    @Column(nullable = false)
    private String accountNumber;
    /**
     * Bank code
     */
    @Column(nullable = false)
    private String bankCode;
    /**
     * IBAN
     */
    private String iban;
    /**
     * Telephone
     */
    @Column(nullable = false)
    private String telephone;
    /**
     * Mail
     */
    @Column(nullable = false)
    @Email(message = "Wrong email address.")
    private String mail;
    /**
     * Street
     */
    @Column(nullable = false)
    private String street;
    /**
     * Post code
     */
    @Column(nullable = false)
    private String zip;
    /**
     * City
     */
    @Column(nullable = false)
    private String city;
    /**
     * Country - specification in enum
     **/
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Countries country;
    /**
     * Note
     */
    private String note;
    /**
     * Set hidden on editing person - original information are saved
     */
    private boolean hidden = false;
    /**
     * Purchases
     */
    @OneToMany(mappedBy = "buyer")
    private List<InvoiceEntity> purchases;
    /**
     * Sales
     */
    @OneToMany(mappedBy = "seller")
    private List<InvoiceEntity> sales;
}
