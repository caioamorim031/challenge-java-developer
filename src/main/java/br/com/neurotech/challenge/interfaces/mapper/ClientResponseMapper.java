package br.com.neurotech.challenge.interfaces.mapper;

import br.com.neurotech.challenge.domain.model.NeurotechClient;
import br.com.neurotech.challenge.interfaces.dto.ClientResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientResponseMapper extends EntityMapper<ClientResponseDTO, NeurotechClient> {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "income", target = "income")
    NeurotechClient toEntity(ClientResponseDTO dto);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "income", target = "income")
    ClientResponseDTO toDto(NeurotechClient entity);
}
