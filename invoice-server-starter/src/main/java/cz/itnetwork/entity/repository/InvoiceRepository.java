package cz.itnetwork.entity.repository;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.filter.InvoiceFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {

    /**
     * Find by buyer and identification number
     */
    List<InvoiceEntity> findByBuyer_IdentificationNumber(String identificationNumber);

    /**
     * Find by seller and identification number
     */
    List<InvoiceEntity> findBySeller_IdentificationNumber(String identificationNumber);

    /**
     * Individual statistic by specific person
     */
   @Query(value = """
                 SELECT new cz.itnetwork.dto.InvoiceStatisticDTO(
                    SUM(allInvoices.price) AS allTimeSum, COUNT(*) AS invoicesCount, SUM(thisYear.price) AS currentYearSum)
                 FROM invoice AS allInvoices
                 LEFT JOIN invoice AS thisYear
                 ON allInvoices.id = thisYear.id
                 AND YEAR(thisYear.issued) = YEAR(CURRENT_DATE)""")
    InvoiceStatisticDTO getGeneralStatistic();

   // @Query( value = "SELECT * FROM invoice WHERE LIMIT :limit", nativeQuery = true)
   //List<InvoiceEntity> getAll(@Param("limit")int limit);





}
