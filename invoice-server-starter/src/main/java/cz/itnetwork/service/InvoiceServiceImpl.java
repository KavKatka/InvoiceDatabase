package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.dto.mapper.PersonMapper;
import cz.itnetwork.entity.InvoiceEntity;
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
        InvoiceEntity newInvoice = invoiceMapper.toEntity(invoiceDTO);

        invoiceRepository.save(newInvoice);

        return invoiceMapper.toDTO(newInvoice);
    }

    @Override
    public InvoiceDTO editInvoice(InvoiceDTO invoiceDTO, long id) {
        InvoiceEntity invoice = invoiceMapper.toEntity(invoiceDTO);

        InvoiceEntity newInvoice = invoice;
        invoiceRepository.save(newInvoice);

        return null;
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
    public InvoiceDTO getDetail(InvoiceDTO invoiceDTO, long id) {
        InvoiceEntity invoice = fetchInvoiceById(id);

        return invoiceMapper.toDTO(invoice);
    }

    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }


}
