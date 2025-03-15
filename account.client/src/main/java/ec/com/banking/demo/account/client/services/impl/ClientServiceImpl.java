package ec.com.banking.demo.account.client.services.impl;

import ec.com.banking.demo.account.client.dtos.ClientDto;
import ec.com.banking.demo.account.client.mapper.ClientMapper;
import ec.com.banking.demo.account.client.models.Client;
import ec.com.banking.demo.account.client.repositories.ClientRepository;
import ec.com.banking.demo.account.client.services.ClientService;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @Override
    public List<ClientDto> listClients() {
        Iterable<Client> clients = clientRepository.findAll();
        return ClientMapper.INSTANCE.clientsToClientDtos(clients);
    }

    @Override
    public Long listNameClient(String nameClient) {
        return clientRepository.findByNombre(nameClient)
                .orElseThrow(() -> new NoSuchElementException("No se encontró información del nombre del cliente:" + nameClient));
    }

    @Override
    public ClientDto insertClient(ClientDto clientDto) {
        Client newClient = clientRepository.save(createClientFromDto(clientDto));
        return ClientMapper.INSTANCE.clientToClientDto(newClient);
    }

    private Client createClientFromDto(ClientDto clientDto) {
        return Client.builder()
                .password(clientDto.password())
                .status("A")
                .nombre(clientDto.nombre())
                .genero(clientDto.genero())
                .edad(clientDto.edad())
                .identificacion(clientDto.identificacion())
                .direccion(clientDto.direccion())
                .telefono(clientDto.telefono())
                .build();
    }

    @Override
    public ClientDto updateClient(Long id, ClientDto clientDto) {
        getClientById(id);
        Client client = ClientMapper.INSTANCE.clientDtoToClient(clientDto);
        client.setId(id);
        client = clientRepository.save(client);
        return ClientMapper.INSTANCE.clientToClientDto(client);
    }

    @Override
    public ClientDto partialUpdateClient(Long id, ClientDto clientDto) {
        getClientById(id);
        Client newClient = updateClientFields(clientDto);
        return ClientMapper.INSTANCE.clientToClientDto(clientRepository.save(newClient));
    }

    private Client updateClientFields(ClientDto clientDto) {
        Client client = new Client();
        if (StringUtils.isNotEmpty(clientDto.password())) client.setPassword(clientDto.password());
        if (StringUtils.isNotEmpty(clientDto.estado())) client.setStatus(clientDto.estado());
        if (StringUtils.isNotEmpty(clientDto.nombre())) client.setNombre(clientDto.nombre());
        if (StringUtils.isNotEmpty(clientDto.genero())) client.setGenero(clientDto.genero());
        if (clientDto.edad() != 0) client.setEdad(clientDto.edad());
        if (StringUtils.isNotEmpty(clientDto.identificacion())) client.setIdentificacion(clientDto.identificacion());
        if (StringUtils.isNotEmpty(clientDto.direccion())) client.setDireccion(clientDto.direccion());
        if (StringUtils.isNotEmpty(clientDto.telefono())) client.setTelefono(clientDto.telefono());
        return client;
    }

    @Override
    public ClientDto getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró información del cliente id:" + id));
        return ClientMapper.INSTANCE.clientToClientDto(client);
    }

    @Override
    public void deleteClient(Long id) {
        getClientById(id);
        clientRepository.deleteById(id);
    }
}
