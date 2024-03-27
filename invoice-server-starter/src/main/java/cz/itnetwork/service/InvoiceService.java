package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.dto.PersonDTO;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


public interface InvoiceService {

    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);


    InvoiceDTO editInvoice(InvoiceDTO invoiceDTO, long id);

    InvoiceDTO removeInvoice(long id);

    InvoiceDTO getDetail(long id);

    List<InvoiceDTO> getAll();

    InvoiceStatisticDTO getGeneralStatistic();





}
