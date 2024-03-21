package cz.itnetwork.entity.repository;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

    InvoiceDTO findByBuyer_IdentificationNumber(String identificationNumber);

    InvoiceDTO findBySeller_IdentificationNumber(String identificationNumber);
}
