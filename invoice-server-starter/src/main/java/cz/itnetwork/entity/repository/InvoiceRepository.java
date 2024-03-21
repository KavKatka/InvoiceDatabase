package cz.itnetwork.entity.repository;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

    List<InvoiceEntity> findByBuyer_IdentificationNumber(String identificationNumber);

    List<InvoiceEntity> findBySeller_IdentificationNumber(String identificationNumber);
}
