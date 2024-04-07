package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.service.InvoiceService;
import cz.itnetwork.service.PersonService;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private PersonService personService;


    @PostMapping("/invoices")
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.addInvoice(invoiceDTO);
    }

    @GetMapping("/invoices")
    public List<InvoiceDTO> getAllFiltered(InvoiceFilter invoiceFilter) {
        return invoiceService.getAllFiltered(invoiceFilter);
    }

    /*@GetMapping("/invoices")
    public List<InvoiceDTO> getAll(){
        return invoiceService.getAll();
    }*/
    @GetMapping("/identification/{identificationNumber}/purchases")
    public List<InvoiceDTO> getPurchases(@PathVariable String identificationNumber) {
        return personService.getPurchases(identificationNumber);
    }

    @GetMapping("/identification/{identificationNumber}/sales")
    public List<InvoiceDTO>getSales(@PathVariable String identificationNumber) {
        return personService.getSales(identificationNumber);
    }

    @GetMapping("/invoices/{id}")
    public InvoiceDTO getDetail(@PathVariable long id) {
        return invoiceService.getDetail(id);
    }

    @PutMapping("/invoices/{id}")
    public InvoiceDTO editInvoice(@RequestBody InvoiceDTO invoiceDTO,@PathVariable long id) {
        return invoiceService.editInvoice(invoiceDTO, id);
    }

    @DeleteMapping("/invoices/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public InvoiceDTO removeInvoice(@PathVariable long id) {
        return invoiceService.removeInvoice(id);
    }

    @GetMapping("/invoices/statistics")
    public InvoiceStatisticDTO getGeneralStatistic(){
        return invoiceService.getGeneralStatistic();
    }


}
