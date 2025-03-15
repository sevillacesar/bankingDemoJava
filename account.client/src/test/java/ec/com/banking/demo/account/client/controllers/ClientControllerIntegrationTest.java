package ec.com.banking.demo.account.client.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.com.banking.demo.account.client.dtos.ClientDto;

import ec.com.banking.demo.account.client.services.ClientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.validation.BindingResult;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientController.class)
public class ClientControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientController clientController;

    @MockBean
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createUserClient_ValidInput_ReturnsCreatedStatus() throws Exception {
        ClientDto clientDto = createMockPersona("nombre", "genero", 31, "identificacion", "direccion", "telefono",
                "password", "estado");

        mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", containsString("Datos creados exitosamente")));
    }

    @Test
    public void testCreateUser_InvalidCommand() throws Exception {
        mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(null)))
                // Verifica que se reciba una respuesta de error de solicitud con el código de
                // estado HTTP 400 BAD REQUEST
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateTaskValidInput() {

        ClientDto clientDto = createMockPersona("nombre", "genero", 23, "identificacion", "direccion", "telefono",
                "password", "estado");

        // Crea un BindingResult simulado sin errores
        BindingResult mockBindingResult = mock(BindingResult.class);
        when(mockBindingResult.hasErrors()).thenReturn(false);

        ResponseEntity<?> responseEntity = clientController.createUserClient(clientDto, mockBindingResult);

        // Verifique que la respuesta tenga un estado 201 Creado
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }

    // Método utilitario para convertir un objeto a JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ClientDto createMockPersona(String nombre, String genero, int edad, String identificacion, String direccion,
                                        String telefono, String password, String estado) {
        ClientDto persona = new ClientDto(nombre, genero, edad, identificacion, direccion, telefono, password, estado);
        return persona;
    }
}
