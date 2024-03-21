package cz.itnetwork.dto.mapper;


import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    InvoiceDTO toDto(InvoiceEntity source);

    InvoiceEntity toEntity(InvoiceDTO source);
}
