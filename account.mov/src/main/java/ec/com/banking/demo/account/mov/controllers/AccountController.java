package ec.com.banking.demo.account.mov.controllers;

import ec.com.banking.demo.account.mov.dtos.AccountReqDto;
import ec.com.banking.demo.account.mov.errors.AccountError;
import ec.com.banking.demo.account.mov.responses.BaseResponse;
import ec.com.banking.demo.account.mov.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    private final AccountError accountError;

    public AccountController(AccountService accountService, AccountError accountError) {
        this.accountService = accountService;
        this.accountError = accountError;
    }

    @GetMapping
    public ResponseEntity<?> listAccount() {
        return ResponseEntity.ok(accountService.listAccounts());
    }

    @GetMapping({ "/{numAccount}" })
    public ResponseEntity<?> findByNumberAccount(@PathVariable("numAccount") String numAccount) {
        return ResponseEntity.ok(accountService.findByNumberAccount(numAccount));
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountReqDto command, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(accountError.validationErrors(result));
        }
        return new ResponseEntity<>(new BaseResponse("Datos creados exitosamente",
                accountService.insertAccount(command)), HttpStatus.CREATED);
    }

    @PutMapping({ "/{accountId}" })
    public ResponseEntity<?> updateAccount(@PathVariable("accountId") String accountId,
                                           @Valid @RequestBody AccountReqDto command, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(accountError.validationErrors(result));
        }
        return new ResponseEntity<>(new BaseResponse("Se actualizo exitosamente",
                accountService.updateAccount(accountId, command)), HttpStatus.OK);
    }

    @DeleteMapping({ "/{accountId}" })
    public ResponseEntity<?> deleteAccount(@PathVariable("accountId") Long accountId) {
        if (!accountService.getAccountById(accountId))
            return new ResponseEntity<>(new BaseResponse("La cuenta no existe"), HttpStatus.NOT_FOUND);
        accountService.deleteAccount(accountId);
        return new ResponseEntity<>(new BaseResponse("Se ha eliminado exitosamente"), HttpStatus.OK);
    }
}
