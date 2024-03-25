package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
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

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PersonMapper personMapper;

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
    public InvoiceDTO editInvoice(InvoiceDTO invoiceDTO, long id) {
        fetchInvoiceById(id);
        InvoiceEntity invoice = invoiceMapper.toEntity(invoiceDTO);
        invoiceMapper.updatedEntity(invoiceDTO, invoice);
        invoice.setId(id);
        InvoiceEntity updatedInvoice = invoiceRepository.save(invoice);

        return invoiceMapper.toDTO(updatedInvoice);
    }

    @Override
    public List<InvoiceDTO> getAll() {
        return invoiceRepository.findAll()
                .stream()
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO removeInvoice(long id) {
        InvoiceEntity invoice = fetchInvoiceById(id);

        invoiceRepository.delete(invoice);

        return invoiceMapper.toDTO(invoice);
    }


    @Override
    public InvoiceDTO getDetail(long id) {
        InvoiceEntity invoice = fetchInvoiceById(id);

        return invoiceMapper.toDTO(invoice);
    }

    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }

    private PersonEntity fetchPersonById(long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with id " + id + " wasn't found in the database."));
    }


}
