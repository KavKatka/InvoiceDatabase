package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;

import java.util.List;


public interface InvoiceService {
    /**
     * Create new invoice
     * @param invoiceDTO specific data
     * @return new invoice
     */
    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

    /**
     * Edit existing invoice
     * @param invoiceDTO data to edit
     * @param id search by id
     * @return edited invoice with same id
     */
    InvoiceDTO editInvoice(InvoiceDTO invoiceDTO, long id);

    /**
     * Remove invoice
     * @param id search by id
     */
    InvoiceDTO removeInvoice(long id);

    /**
     * Get detail of invoice
     * @param id search by id
     */
    InvoiceDTO getDetail(long id);

    /**
     * Get all invoices in database
     */
    List<InvoiceDTO> getAllFiltered(InvoiceFilter invoiceFilter);

   // List <InvoiceDTO> getAll();


    /**
     * Get general statistic per year
     */
    InvoiceStatisticDTO getGeneralStatistic();





}
