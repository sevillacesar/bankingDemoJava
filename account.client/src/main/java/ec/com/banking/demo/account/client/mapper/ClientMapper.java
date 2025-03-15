package ec.com.banking.demo.account.client.mapper;

import ec.com.banking.demo.account.client.dtos.ClientDto;
import ec.com.banking.demo.account.client.models.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
    ClientDto clientToClientDto(Client client);
    Client clientDtoToClient(ClientDto clientDto);
    List<ClientDto> clientsToClientDtos(Iterable<Client> clients);
    List<Client> clientDtosToClients(List<ClientDto> clientDtos);
}
