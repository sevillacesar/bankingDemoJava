package ec.com.banking.demo.account.client.services.impl;

import ec.com.banking.demo.account.client.dtos.ClientDto;
import ec.com.banking.demo.account.client.mapper.ClientMapper;
import ec.com.banking.demo.account.client.models.Client;
import ec.com.banking.demo.account.client.repositories.ClientRepository;
import ec.com.banking.demo.account.client.services.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDto> listClients() {
        Iterable<Client> clients = clientRepository.findAll();
        return clientMapper.clientsToClientDtos(clients);
    }

    @Override
    @Transactional(readOnly = true)
    public Long listNameClient(String nameClient) {
        return clientRepository.findByNombre(nameClient)
                .orElseThrow(() -> new NoSuchElementException("No se encontró información del nombre del cliente:" + nameClient));
    }

    @Override
    @Transactional
    public ClientDto insertClient(ClientDto clientDto) {
        Client newClient = clientRepository.save(clientMapper.clientDtoToClient(clientDto));
        return clientMapper.clientToClientDto(newClient);
    }

    @Override
    @Transactional
    public ClientDto updateClient(Long id, ClientDto clientDto) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró información del cliente id:" + id));
        clientMapper.replaceClientFromDto(clientDto, existingClient);
        existingClient.setId(id);
        Client updatedClient = clientRepository.save(existingClient);
        return clientMapper.clientToClientDto(updatedClient);
    }

    @Override
    @Transactional
    public ClientDto partialUpdateClient(Long id, ClientDto clientDto) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró información del cliente id:" + id));
        clientMapper.updateClientFromDto(clientDto, existingClient);
        existingClient.setId(id);
        Client updatedClient = clientRepository.save(existingClient);
        return clientMapper.clientToClientDto(updatedClient);
    }

    @Override
    @Transactional
    public void deleteClient(Long id) {
        clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró información del cliente id:" + id));
        clientRepository.deleteById(id);
    }
}
