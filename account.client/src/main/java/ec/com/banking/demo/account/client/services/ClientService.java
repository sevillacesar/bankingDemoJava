package ec.com.banking.demo.account.client.services;

import ec.com.banking.demo.account.client.dtos.ClientDto;

import java.util.List;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

public interface ClientService {

    List<ClientDto> listClients();

    ClientDto updateClient(Long id, ClientDto client);

    ClientDto partialUpdateClient(Long id, ClientDto client);

    Long listNameClient(String nombreCliente);

    ClientDto insertClient(ClientDto clientDto);

    ClientDto getClientById(Long id);

    void deleteClient(Long id);
}
