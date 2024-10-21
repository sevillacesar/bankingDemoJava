package ec.com.banking.demo.account.client.controllers;

import ec.com.banking.demo.account.client.dtos.UserDto;
import ec.com.banking.demo.account.client.errors.ClientError;
import ec.com.banking.demo.account.client.responses.BaseResponse;
import ec.com.banking.demo.account.client.services.ClientService;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "http://host.docker.internal:5001")
public class ClientController {

    private final ClientService clientService;

    private final ClientError clientError;

    public ClientController(ClientService clientService, ClientError clientError) {
        this.clientService = clientService;
        this.clientError = clientError;
    }

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(clientService.listClients());
    }

    @GetMapping({ "/{nameClient}" })
    public ResponseEntity<?> listById(@NonNull @PathVariable("nameClient") String nameClient) {
        try {
            return ResponseEntity.ok(clientService.listNameClient(nameClient));
        } catch (NoSuchElementException e) {
            return clientError.handleNotFound(e);
        } catch (IllegalArgumentException e) {
            return clientError.handleBadRequest(e);
        } catch (DataAccessException e) {
            return clientError.handleInternalError(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUserClient(@Valid @RequestBody UserDto command, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(clientError.validationErrors(result));
        }
        try {
            clientService.insertClient(command);
            return new ResponseEntity<>(new BaseResponse("Datos creados exitosamente", command), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return clientError.handleBadRequest(e);
        } catch (DataAccessException e) {
            return clientError.handleInternalError(e);
        }
    }

    @PutMapping({ "/{clientId}" })
    public ResponseEntity<?> updateClient(@PathVariable("clientId") Long clientId, @Valid @RequestBody UserDto command,
            BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(clientError.validationErrors(result));
        }
        try {
            clientService.updateClient(clientId, command);
            return new ResponseEntity<>(new BaseResponse("Se actualizo exitosamente", command), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return clientError.handleNotFound(e);
        } catch (IllegalArgumentException e) {
            return clientError.handleBadRequest(e);
        } catch (DataAccessException e) {
            return clientError.handleInternalError(e);
        }
    }

    @PatchMapping({ "/{clientId}" })
    public ResponseEntity<?> partialUpdateClient(@PathVariable("clientId") Long clientId,
            @Valid @RequestBody UserDto command, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(clientError.validationErrors(result));
        }
        try {
            clientService.partialUpdateClient(clientId, command);
            return new ResponseEntity<>(new BaseResponse("Se actualizo exitosamente", command), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return clientError.handleNotFound(e);
        } catch (IllegalArgumentException e) {
            return clientError.handleBadRequest(e);
        } catch (DataAccessException e) {
            return clientError.handleInternalError(e);
        }
    }

    @DeleteMapping({ "/{clientId}" })
    public ResponseEntity<?> deleteClient(@PathVariable("clientId") Long clientId) {
        try {
            clientService.deleteClient(clientId);
            return new ResponseEntity<>(new BaseResponse("Se ha eliminado exitosamente"), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return clientError.handleNotFound(e);
        } catch (IllegalArgumentException e) {
            return clientError.handleBadRequest(e);
        } catch (DataAccessException e) {
            return clientError.handleInternalError(e);
        }
    }
}
