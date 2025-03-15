package ec.com.banking.demo.account.mov.controllers;

import ec.com.banking.demo.account.mov.dtos.MovementDto;
import ec.com.banking.demo.account.mov.errors.AccountError;
import ec.com.banking.demo.account.mov.responses.BaseResponse;
import ec.com.banking.demo.account.mov.services.MovementService;
import jakarta.validation.Valid;
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
@RequestMapping("/movements")
public class MovementsController {

    private final MovementService movementService;

    private final AccountError accountError;

    public MovementsController(MovementService movementService, AccountError accountError) {
        this.movementService = movementService;
        this.accountError = accountError;
    }

    @GetMapping
    public ResponseEntity<?> listMovements() {
        return ResponseEntity.ok(movementService.listMovements());
    }

    @PostMapping
    public ResponseEntity<?> createMovement(@Valid @RequestBody MovementDto command, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(accountError.validationErrors(result));
        }
        try {
            return new ResponseEntity<>(new BaseResponse("Datos creados exitósamente",
                    movementService.createMovement(command)), HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return accountError.handleNotFound(e);
        } catch (IllegalArgumentException e) {
            return accountError.handleBadRequest(e);
        } catch (DataAccessException e) {
            return accountError.handleInternalError(e);
        }
    }

    @PutMapping({ "/{accountNumber}" })
    public ResponseEntity<?> updateMovement(@PathVariable("accountNumber") String accountNumber,
            @Valid @RequestBody MovementDto command, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(accountError.validationErrors(result));
        }
        try {
            return new ResponseEntity<>(new BaseResponse("Se actualizo exitosamente",
                    movementService.updateMovement(accountNumber, command)), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return accountError.handleNotFound(e);
        } catch (IllegalArgumentException e) {
            return accountError.handleBadRequest(e);
        } catch (DataAccessException e) {
            return accountError.handleInternalError(e);
        }
    }

}
