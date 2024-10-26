package org.aston.tickets.mappers;

import org.aston.tickets.dto.TicketsResponseDto;
import org.aston.tickets.model.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TicketsMapper {

    List<TicketsResponseDto> entityToDto(List<Ticket> entity);
}
