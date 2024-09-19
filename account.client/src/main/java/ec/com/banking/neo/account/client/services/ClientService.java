package ec.com.banking.neo.account.client.services;

import ec.com.banking.neo.account.client.dtos.UserDto;
import ec.com.banking.neo.account.client.models.Client;

import java.util.List;
import java.util.Optional;

/**
 * @author cesarsevilla
 * @project bankingDemoJava
 */

public interface ClientService {

    List<Client> listClients();

    Optional<Client> listClient(Long id);

    Optional<Optional<Client>> updateClient(Long id, UserDto client);

    Optional<Client> partialUpdateClient(Long id, UserDto client);

    int listNameClient(String nombreCliente);

    void insertClient(UserDto userDto);

    boolean getClientById(Long id);

    void deleteClient(Long id);
}
