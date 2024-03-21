package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;

import java.util.List;


public interface InvoiceService {

    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

    //InvoiceDTO editInvoice();

    InvoiceDTO removeInvoice(long id);

    InvoiceDTO getDetail(InvoiceDTO invoiceDTO, long id);

    List<InvoiceDTO> getAll();

    InvoiceDTO getPurchases(String identificationNumber);

    InvoiceDTO getSales(String identificationNumber);



}
