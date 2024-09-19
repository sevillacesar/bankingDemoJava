package ec.com.banking.neo.account.client.services.impl;

import ec.com.banking.neo.account.client.dtos.UserDto;
import ec.com.banking.neo.account.client.models.Client;
import ec.com.banking.neo.account.client.models.Person;
import ec.com.banking.neo.account.client.repositories.ClientRepository;
import ec.com.banking.neo.account.client.repositories.PersonRepository;
import ec.com.banking.neo.account.client.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author cesarsevilla
 * @project bankingDemoJava
 */

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Client> listClients() {
        return (List<Client>) clientRepository.findAll();
    }

    @Override
    public Optional<Client> listClient(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public int listNameClient(String nameClient) {
        return clientRepository.findByNombre(nameClient);
    }

    @Override
    public void insertClient(UserDto userDto) {
        clientRepository.save(createClientFromPerson(createPersonFromDto(userDto), userDto.getPassword()));
    }

    private Person createPersonFromDto(UserDto userDto) {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        return new Person(uuidAsString, userDto.getNombre(), userDto.getGenero(), userDto.getEdad(),
                userDto.getIdentificacion(), userDto.getDireccion(), userDto.getTelefono());
    }

    private Client createClientFromPerson(Person person, String contrasenia) {
        return Client.builder()
                .password(contrasenia)
                .status("A")
                .personId(person)
                .build();
    }

    @Override
    public Optional<Optional<Client>> updateClient(Long id, UserDto clientDto) {
        return clientRepository.findById(id).map(client -> {
            personRepository.findById(client.getPersonId().getId()).ifPresent(person -> {
                person.setNombre(clientDto.getNombre());
                person.setGenero(clientDto.getGenero());
                person.setEdad(clientDto.getEdad());
                person.setIdentificacion(clientDto.getIdentificacion());
                person.setDireccion(clientDto.getDireccion());
                person.setTelefono(clientDto.getTelefono());

                client.setPassword(clientDto.getPassword());
                client.setStatus(clientDto.getEstado());
                clientRepository.save(client);
            });
            return Optional.of(client);
        });
    }

    @Override
    public Optional<Client> partialUpdateClient(Long id, UserDto clientDto) {
        return clientRepository.findById(id).map(client -> {
            personRepository.findById(client.getPersonId().getId()).ifPresent(person -> {
                updatePersonFields(person, clientDto);
                updateClientFields(client, clientDto);
                clientRepository.save(client);
            });
            return client;
        });
    }

    private void updatePersonFields(Person person, UserDto clientDto) {
        if (clientDto.getNombre() != null) {
            person.setNombre(clientDto.getNombre());
        }
        if (clientDto.getGenero() != null) {
            person.setGenero(clientDto.getGenero());
        }
        if (clientDto.getEdad() != 0) {
            person.setEdad(clientDto.getEdad());
        }
        if (clientDto.getIdentificacion() != null) {
            person.setIdentificacion(clientDto.getIdentificacion());
        }
        if (clientDto.getDireccion() != null) {
            person.setDireccion(clientDto.getDireccion());
        }
        if (clientDto.getTelefono() != null) {
            person.setTelefono(clientDto.getTelefono());
        }
    }

    private void updateClientFields(Client client, UserDto clientDto) {
        if (clientDto.getPassword() != null) {
            client.setPassword(clientDto.getPassword());
        }
        if (clientDto.getEstado() != null) {
            client.setStatus(clientDto.getEstado());
        }
    }

    @Override
    public boolean getClientById(Long id) {
        return clientRepository.findById(id).isPresent();
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
