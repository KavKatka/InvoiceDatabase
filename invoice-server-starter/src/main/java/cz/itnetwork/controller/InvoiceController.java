package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/invoices")
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO){
        return invoiceService.addInvoice(invoiceDTO);
    }

    @GetMapping("/invoices")
    public List<InvoiceDTO> getAll(){
        return invoiceService.getAll();
    }

    @GetMapping("/invoices")
    public InvoiceDTO getPurchases(@PathVariable String identificationNumber){
        return invoiceService.getPurchases(identificationNumber);
    }

    @GetMapping("/invoices")
    public InvoiceDTO getSales(@PathVariable String identificationNumber){
        return invoiceService.getSales(identificationNumber);
    }

    @GetMapping("/invoices/{id}")
    public InvoiceDTO getDetail(@RequestBody InvoiceDTO invoiceDTO,@PathVariable long id){
        return invoiceService.getDetail(invoiceDTO, id);
    }

    //@PutMapping("/invoices/{id}")
    //public InvoiceDTO editInvoice(@PathVariable long id){
    //    return invoiceService.editInvoice();
    //}

    @DeleteMapping("/invoices/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public InvoiceDTO removeInvoice(@PathVariable long id){
        return invoiceService.removeInvoice(id);
    }

}
