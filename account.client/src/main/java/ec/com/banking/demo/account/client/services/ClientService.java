package ec.com.banking.demo.account.client.services;

import ec.com.banking.demo.account.client.dtos.UserDto;
import ec.com.banking.demo.account.client.models.Client;

import java.util.List;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

public interface ClientService {

    List<Client> listClients();

    Client updateClient(Long id, UserDto client);

    Client partialUpdateClient(Long id, UserDto client);

    Long listNameClient(String nombreCliente);

    void insertClient(UserDto userDto);

    Client getClientById(Long id);

    void deleteClient(Long id);
}
