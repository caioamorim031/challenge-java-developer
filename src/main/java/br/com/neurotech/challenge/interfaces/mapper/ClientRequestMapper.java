package br.com.neurotech.challenge.interfaces.mapper;

import br.com.neurotech.challenge.domain.model.NeurotechClient;
import br.com.neurotech.challenge.interfaces.dto.ClientRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientRequestMapper extends EntityMapper<ClientRequestDTO, NeurotechClient> {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "income", target = "income")
    NeurotechClient toEntity(ClientRequestDTO dto);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "income", target = "income")
    ClientRequestDTO toDto(NeurotechClient entity);
}
