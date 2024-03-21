package cz.itnetwork.dto.mapper;


import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {


    InvoiceDTO toDTO(InvoiceEntity source);

    InvoiceEntity toEntity(InvoiceDTO source);

    List<InvoiceDTO> toDTOs(List<InvoiceEntity> source);




}
