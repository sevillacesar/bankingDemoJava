package ec.com.banking.demo.account.client.services.impl;

import ec.com.banking.demo.account.client.dtos.UserDto;
import ec.com.banking.demo.account.client.models.Client;
import ec.com.banking.demo.account.client.repositories.ClientRepository;
import ec.com.banking.demo.account.client.services.ClientService;
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
    public List<Client> listClients() {
        return (List<Client>) clientRepository.findAll();
    }

    @Override
    public Long listNameClient(String nameClient) {
        return clientRepository.findByNombre(nameClient)
                .orElseThrow(() -> new NoSuchElementException("No se encontró información del nombre del cliente:" + nameClient));
    }

    @Override
    public void insertClient(UserDto userDto) {
        clientRepository.save(createClientFromDto(userDto));
    }

    private Client createClientFromDto(UserDto userDto) {
        return Client.builder()
                .password(userDto.password())
                .status("A")
                .nombre(userDto.nombre())
                .genero(userDto.genero())
                .edad(userDto.edad())
                .identificacion(userDto.identificacion())
                .direccion(userDto.direccion())
                .telefono(userDto.telefono())
                .build();
    }

    @Override
    public Client updateClient(Long id, UserDto clientDto) {
        Client client = getClientById(id);
        client.setNombre(clientDto.nombre());
        client.setGenero(clientDto.genero());
        client.setEdad(clientDto.edad());
        client.setIdentificacion(clientDto.identificacion());
        client.setDireccion(clientDto.direccion());
        client.setTelefono(clientDto.telefono());
        client.setPassword(clientDto.password());
        client.setStatus(clientDto.estado());
        return clientRepository.save(client);
    }

    @Override
    public Client partialUpdateClient(Long id, UserDto clientDto) {
        Client client = getClientById(id);
        updateClientFields(client, clientDto);
        return clientRepository.save(client);
    }

    private void updateClientFields(Client client, UserDto clientDto) {
        if (clientDto.password() != null) {
            client.setPassword(clientDto.password());
        }
        if (clientDto.estado() != null) {
            client.setStatus(clientDto.estado());
        }
        if (clientDto.nombre() != null) {
            client.setNombre(clientDto.nombre());
        }
        if (clientDto.genero() != null) {
            client.setGenero(clientDto.genero());
        }
        if (clientDto.edad() != 0) {
            client.setEdad(clientDto.edad());
        }
        if (clientDto.identificacion() != null) {
            client.setIdentificacion(clientDto.identificacion());
        }
        if (clientDto.direccion() != null) {
            client.setDireccion(clientDto.direccion());
        }
        if (clientDto.telefono() != null) {
            client.setTelefono(clientDto.telefono());
        }
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró información del cliente id:" + id));
    }

    @Override
    public void deleteClient(Long id) {
        getClientById(id);
        clientRepository.deleteById(id);
    }
}
