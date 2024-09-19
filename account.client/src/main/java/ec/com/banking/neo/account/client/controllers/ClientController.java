package ec.com.banking.neo.account.client.controllers;

import ec.com.banking.neo.account.client.dtos.UserDto;
import ec.com.banking.neo.account.client.errors.ClientError;
import ec.com.banking.neo.account.client.models.Client;
import ec.com.banking.neo.account.client.responses.BaseResponse;
import ec.com.banking.neo.account.client.services.ClientService;
import ec.com.banking.neo.account.client.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author cesarsevilla
 * @project bankingDemoJava
 */

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "http://host.docker.internal:5001")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private PersonService personService;

    @Autowired
    private ClientError clientError;

    @GetMapping
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(clientService.listClients());
    }

    @GetMapping({ "/{nameClient}" })
    public ResponseEntity<?> listById(@PathVariable("nameClient") String nameClient) {
        return ResponseEntity.ok(clientService.listNameClient(nameClient));
    }

    @PostMapping
    public ResponseEntity<?> createUserClient(@Valid @RequestBody UserDto command, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(clientError.validationErrors(result));
        }

        try {
            clientService.insertClient(command);
            return new ResponseEntity<>(new BaseResponse("Datos creados exitosamente"), HttpStatus.CREATED);
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
            Optional<Optional<Client>> client = clientService.updateClient(clientId, command);
            if (client.isPresent())
                return new ResponseEntity<>(new BaseResponse("Se actualizo exitosamente"), HttpStatus.OK);
            else
                return new ResponseEntity<>(new BaseResponse("El cliente no existe"), HttpStatus.NOT_FOUND);
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
            Optional<Client> client = clientService.partialUpdateClient(clientId, command);
            if (client.isPresent())
                return new ResponseEntity<>(new BaseResponse("Se actualizo exitosamente"), HttpStatus.OK);
            else
                return new ResponseEntity<>(new BaseResponse("El cliente no existe"), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return clientError.handleBadRequest(e);
        } catch (DataAccessException e) {
            return clientError.handleInternalError(e);
        }
    }

    @DeleteMapping({ "/{clientId}" })
    public ResponseEntity<?> deleteClient(@PathVariable("clientId") Long clientId) {
        try {
            if (clientService.getClientById(clientId)) {
                clientService.deleteClient(clientId);
                return new ResponseEntity<>(new BaseResponse("Se ha eliminado exitosamente"), HttpStatus.OK);
            } else
                return new ResponseEntity<>(new BaseResponse("El cliente no existe"), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return clientError.handleBadRequest(e);
        } catch (DataAccessException e) {
            return clientError.handleInternalError(e);
        }
    }
}
