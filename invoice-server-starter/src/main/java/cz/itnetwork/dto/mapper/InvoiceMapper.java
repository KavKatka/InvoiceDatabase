package cz.itnetwork.dto.mapper;


import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    /**
     * DTO to entity
     */
    InvoiceDTO toDTO(InvoiceEntity source);

    /**
     * Entity to DTO
     */
    InvoiceEntity toEntity(InvoiceDTO source);

    /**
     * List DTO to List Invoice entity
     */
    List<InvoiceDTO> toDTOs(List<InvoiceEntity> source);

    /**
     * Mapping targets for update invoice
     */
    @Mapping(target = "buyer", ignore = true)
    @Mapping(target = "seller", ignore = true)
    void updatedEntity(InvoiceDTO source, @MappingTarget InvoiceEntity target);






}
