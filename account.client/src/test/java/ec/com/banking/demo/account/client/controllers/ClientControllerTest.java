package ec.com.banking.demo.account.client.controllers;

import ec.com.banking.demo.account.client.dtos.ClientDto;
import ec.com.banking.demo.account.client.services.ClientService;
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
    private ClientController clientController;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    void testListClients_Success() {
        // Simula una lista de ClientDto que el servicio debería devolver
        List<ClientDto> mockClientDtos = Arrays.asList(
                new ClientDto("Juan", "Masculino", 30, "123", "Calle A", "111", null, "active"),
                new ClientDto("Maria", "Femenino", 25, "456", "Calle B", "222", null,"active")
        );

        // Define el comportamiento del servicio mockeado
        when(clientService.listClients()).thenReturn(mockClientDtos);

        // Llama al método del controlador
        ResponseEntity<?> responseEntity = clientController.list();

        // Verifica la respuesta
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockClientDtos, responseEntity.getBody());
    }

    @Test
    void testListById_Success() {
        // Simula el nombre del cliente y el ClientDto que el servicio debería devolver
        String nombreCliente = "ClienteExistente";
        //ClientDto mockClientDto = new ClientDto(nombreCliente, "Masculino", 30, "123", "Calle Falsa 123", "555-1234", null,"active");

        // Define el comportamiento del servicio mockeado
        when(clientService.listNameClient(nombreCliente)).thenReturn(1L);

        // Llama al método del controlador
        ResponseEntity<?> response = clientController.listById(nombreCliente);

        // Verifica la respuesta
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody());
    }

}