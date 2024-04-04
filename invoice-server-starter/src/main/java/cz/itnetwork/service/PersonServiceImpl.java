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
package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.dto.mapper.PersonMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    /**
     * DI person mapper
     */
    @Autowired
    private PersonMapper personMapper;
    /**
     * DI person repository
     */
    @Autowired
    private PersonRepository personRepository;
    /**
     * DI invoice mapper
     */
    @Autowired
    private InvoiceMapper invoiceMapper;
    /**
     * DI invoice repository
     */
    @Autowired
    private InvoiceRepository invoiceRepository;

    /**
     * Method to add person to database in DTO format
     * @param personDTO Person to create
     */
    public PersonDTO addPerson(PersonDTO personDTO) {
        PersonEntity entity = personMapper.toEntity(personDTO);
        PersonEntity newEntity = personRepository.save(entity);

        return personMapper.toDTO(newEntity);
    }

    /**
     * Method to edit existing person and original information set hidden
     * @param personDTO person to edit
     * @param id find person to edit by id
     * @return new edited person
     */
    @Override
    public PersonDTO editPerson(PersonDTO personDTO, long id) {

        PersonEntity person = fetchPersonById(id);

        person.setHidden(true);
        personRepository.save(person);

        PersonEntity newPerson = personMapper.toEntity(personDTO);
        personRepository.save(newPerson);

        return personMapper.toDTO(newPerson);
    }

    /**
     * Method to get detail information for specific person by id
     * @param id search person by id
     * @return details of person
     */
    @Override
    public PersonDTO getDetail(long id) {

        PersonEntity person = fetchPersonById(id);

        return personMapper.toDTO(person);
    }

    /**
     * Method to get List of sales by identification number of person
     * @param identificationNumber identification number of specific person
     * @return all sales by specific person
     */
    @Override
    public List<InvoiceDTO> getSales(String identificationNumber) {

        return invoiceMapper.toDTOs(invoiceRepository.findByBuyer_IdentificationNumber(identificationNumber));
    }

    /**
     * Method to get List of purchases by identification number of specific person
     * @param identificationNumber identification number of specific person
     * @return all purchases by specific person
     */
    @Override
    public List<InvoiceDTO> getPurchases(String identificationNumber) {

        return invoiceMapper.toDTOs(invoiceRepository.findBySeller_IdentificationNumber(identificationNumber));
    }

    /**
     * Method to delete person by id
     * @param personId Person to delete
     */
    @Override
    public void removePerson(long personId) {
        try {
            PersonEntity person = fetchPersonById(personId);
            person.setHidden(true);

            personRepository.save(person);
        } catch (NotFoundException ignored) {
            // The contract in the interface states, that no exception is thrown, if the entity is not found.
        }
    }

    /**
     * Method to get all persons in database
     */
    @Override
    public List<PersonDTO> getAll() {
        return personRepository.findByHidden(false)
                .stream()
                .map(i -> personMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    /**
     * Method to get List of statistic by specific person
     */
    @Override
    public List<PersonStatisticDTO> getIndividualStatistic(){
        return personRepository.getIndividualStatistic();
    }


    // region: Private methods

    /**
     * <p>Attempts to fetch a person.</p>
     * <p>In case a person with the passed [id] doesn't exist a [{@link org.webjars.NotFoundException}] is thrown.</p>
     *
     * @param id Person to fetch
     * @return Fetched entity
     * @throws org.webjars.NotFoundException In case a person with the passed [id] isn't found
     */
    private PersonEntity fetchPersonById(long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with id " + id + " wasn't found in the database."));
    }
    // endregion
}
