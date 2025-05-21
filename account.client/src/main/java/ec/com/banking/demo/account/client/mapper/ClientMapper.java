package ec.com.banking.demo.account.client.mapper;

import ec.com.banking.demo.account.client.dtos.ClientDto;
import ec.com.banking.demo.account.client.models.Client;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client clientDtoToClient(ClientDto clientDto);
    ClientDto clientToClientDto(Client client);
    void replaceClientFromDto(ClientDto clientDto, @MappingTarget Client client);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClientFromDto(ClientDto clientDto, @MappingTarget Client client);

    List<ClientDto> clientsToClientDtos(Iterable<Client> clients);

}
