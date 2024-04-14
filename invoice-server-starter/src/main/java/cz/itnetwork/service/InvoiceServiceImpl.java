package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.dto.mapper.PersonMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import cz.itnetwork.entity.repository.specification.InvoiceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    @Override
    public InvoiceDTO editInvoice(InvoiceDTO invoiceDTO, Long id) {
        InvoiceEntity invoice = fetchInvoiceById(id);
        invoiceDTO.setId(id);
        invoiceMapper.updatedEntity(invoiceDTO, invoice);
        InvoiceEntity updatedInvoice = invoiceRepository.save(invoice);

        return invoiceMapper.toDTO(updatedInvoice);
    }

    @Override
    public List<InvoiceDTO> getAllFiltered(InvoiceFilter invoiceFilter) {
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);

        return invoiceRepository.findAll(invoiceSpecification, PageRequest.of(0, invoiceFilter.getLimit()))
                .stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO removeInvoice(Long id) {
        InvoiceEntity invoice = fetchInvoiceById(id);
        invoiceRepository.delete(invoice);

        return invoiceMapper.toDTO(invoice);
    }

    @Override
    public InvoiceDTO getDetail(Long id) {
        InvoiceEntity invoice = fetchInvoiceById(id);

        return invoiceMapper.toDTO(invoice);
    }

    @Override
    public InvoiceStatisticDTO getGeneralStatistic() {
        return invoiceRepository.getGeneralStatistic();
    }

    /**
     * Find invoice by id
     *
     * @param id search by id
     * @return invoice by id or throw NotFoundException
     */
    private InvoiceEntity fetchInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }

    /**
     * Find person by id
     *
     * @param id search by id
     * @return person by id or throw NotFoundException
     */
    private PersonEntity fetchPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with id " + id + " wasn't found in the database."));
    }


}
