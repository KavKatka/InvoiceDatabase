package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticDTO;

import java.util.List;

public interface PersonService {

    /**
     * Creates a new person
     *
     * @param personDTO Person to create
     * @return newly created person
     */
    PersonDTO addPerson(PersonDTO personDTO);

    /**
     * Edit existing person
     * @param personDTO information of person
     * @param id edited person get new id
     * @return new edited person
     */
    PersonDTO editPerson(PersonDTO personDTO, long id);

    /**
     * Get detail by specific person
     * @param id search person by id
     * @return detail information of person
     */
    PersonDTO getDetail(long id);

    /**
     * <p>Sets hidden flag to true for the person with the matching [id]</p>
     * <p>In case a person with the passed [id] isn't found, the method <b>silently fails</b></p>
     *
     * @param id Person to delete
     */
    void removePerson(long id);

    /**
     * Fetches all non-hidden persons
     *
     * @return List of all non-hidden persons
     */
    List<PersonDTO> getAll();

    /**
     * Get all purchases
     * @param identificationNumber search purchases by specific identification number
     * @return  List of all purchases by specific person
     */
    List<InvoiceDTO> getPurchases(String identificationNumber);
    /**
     * Get all sales
     * @param identificationNumber search sales by specific identification number
     * @return  List of all sales by specific person
     */
    List<InvoiceDTO> getSales(String identificationNumber);

    /**
     * Get individual statistic
     */
    List<PersonStatisticDTO> getIndividualStatistic();

}
