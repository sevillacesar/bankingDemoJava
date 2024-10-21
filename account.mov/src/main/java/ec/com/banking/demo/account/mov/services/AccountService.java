package ec.com.banking.demo.account.mov.services;

import ec.com.banking.demo.account.mov.dtos.AccountDto;
import ec.com.banking.demo.account.mov.models.Account;

import java.util.List;
import java.util.Optional;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

public interface AccountService {
    List<Account> listAccounts();

    Optional<Account> getAccount(Long id);

    Account findByNumberAccount(String numAccount);

    Account updateAccount(String id, AccountDto cuenta);

    void insertAccount(AccountDto cuenta);

    boolean getAccountById(Long id);

    void deleteAccount(Long id);
}
