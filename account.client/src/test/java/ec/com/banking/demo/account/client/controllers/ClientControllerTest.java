package ec.com.banking.demo.account.client.controllers;

import ec.com.banking.demo.account.client.mapper.ClientMapper;
import ec.com.banking.demo.account.client.models.Client;
import ec.com.banking.demo.account.client.repositories.ClientRepository;
import ec.com.banking.demo.account.client.services.ClientService;
import ec.com.banking.demo.account.client.services.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientServiceImpl clientServiceImpl;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void testListById() {
        // Simula el nombre del cliente y el cliente encontrado
        String nombreCliente = "ClienteExistente";
        Client cliente = Client.builder()
                .id(1L)
                .password("securePassword")
                .status("active")
                .nombre("Juan")
                .genero("Masculino")
                .edad(30)
                .identificacion("ABC123456")
                .direccion("Calle Falsa 123")
                .telefono("555-1234")
                .build();

        // Define el comportamiento del servicio de cliente
        when(clientService.listNameClient(nombreCliente)).thenReturn(1L);

        // Llama al método del controlador
        ResponseEntity<?> response = clientController.listById(nombreCliente);

        // Verifica que se devuelva una respuesta exitosa con el código de estado HTTP
        // 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Verifica que el cuerpo de la respuesta contiene el cliente esperado
        assertEquals(1L, response.getBody());
    }

    @Test
    void testListClients() {
        // Mocking del método de servicio para devolver una lista de clientes
        List<Client> mockCliente = Arrays.asList(
                createMockClient(1L, "", ""),
                createMockClient(2L, "", ""));
        List<Client> clients = ClientMapper.INSTANCE.clientDtosToClients(clientServiceImpl.listClients());
        when(clients).thenReturn(mockCliente);

        // Llamar al endpoint para obtener ResponseEntity
        ResponseEntity<?> responseEntity = clientController.list();

        // Assertions
        assertEquals(200, responseEntity.getStatusCodeValue()); // Assuming 200 for OK status
        assertEquals(mockCliente, responseEntity.getBody()); // Check if the returned list matches the mock data
    }

    @Test
    void testListClientsUsingRepositoryClass() {
        // crea data de ejemplo
        List<Client> mockCliente = Arrays.asList(
                createMockClient(1L, "", ""),
                createMockClient(2L, "", "")

        );

        // Mock del comportamiento del repositorio
        when(clientRepository.findAll()).thenReturn(mockCliente);

        // Llame al método para probar
        List<Client> result = ClientMapper.INSTANCE.clientDtosToClients(clientServiceImpl.listClients());
        assertEquals(2, result.size());
    }

    // Método auxiliar para crear un objeto cliente simulado
    private Client createMockClient(long id, String password, String status) {
        return Client.builder()
                .id(id)
                .password(password)
                .status(status)
                .build();
    }
}