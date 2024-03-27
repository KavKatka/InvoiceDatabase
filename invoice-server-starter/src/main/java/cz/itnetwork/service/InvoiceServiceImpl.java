package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticDTO;
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
public class InvoiceServiceImpl implements InvoiceService {
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
     * Method to create new invoice
     * @param invoiceDTO specific data to create new invoice
     * @return new invoice
     */
    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity invoice = invoiceMapper.toEntity(invoiceDTO);
        PersonEntity seller = fetchPersonById(invoiceDTO.getSeller().getId());
        PersonEntity buyer = fetchPersonById(invoiceDTO.getBuyer().getId());
        invoice.setSeller(seller);
        invoice.setBuyer(buyer);

        invoiceRepository.save(invoice);

        return invoiceMapper.toDTO(invoice);
    }

    /**
     * Method to edit existing invoice
     * @param invoiceDTO data for editing
     * @param id search by id
     * @return edited invoice with same id
     */
    @Override
    public InvoiceDTO editInvoice(InvoiceDTO invoiceDTO, long id) {
        fetchInvoiceById(id);
        InvoiceEntity invoice = invoiceMapper.toEntity(invoiceDTO);
        invoiceMapper.updatedEntity(invoiceDTO, invoice);
        invoice.setId(id);
        InvoiceEntity updatedInvoice = invoiceRepository.save(invoice);

        return invoiceMapper.toDTO(updatedInvoice);
    }

    /**
     * Method to get all invoices
     */
    @Override
    public List<InvoiceDTO> getAll() {
        return invoiceRepository.findAll()
                .stream()
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    /**
     * Method to delete invoice
     * @param id search by id to delete invoice
     */
    @Override
    public InvoiceDTO removeInvoice(long id) {
        InvoiceEntity invoice = fetchInvoiceById(id);

        invoiceRepository.delete(invoice);

        return invoiceMapper.toDTO(invoice);
    }

    /**
     * Get detail of specific invoice by id
     * @param id search invoice
     * @return detail of invoice
     */
    @Override
    public InvoiceDTO getDetail(long id) {
        InvoiceEntity invoice = fetchInvoiceById(id);

        return invoiceMapper.toDTO(invoice);
    }

    /**
     * Get general statistic per year
     */
    @Override
    public InvoiceStatisticDTO getGeneralStatistic(){
        return invoiceRepository.getGeneralStatistic();
    }

    /**
     * Find invoice by id
     * @param id search by id
     * @return invoice by id or throw NotFoundException
     */
    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }
    /**
     * Find person by id
     * @param id search by id
     * @return person by id or throw NotFoundException
     */
    private PersonEntity fetchPersonById(long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with id " + id + " wasn't found in the database."));
    }


}
