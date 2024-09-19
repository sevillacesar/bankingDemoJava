package ec.com.banking.neo.account.mov.services;

import ec.com.banking.neo.account.mov.dtos.AccountDto;
import ec.com.banking.neo.account.mov.models.Account;

import java.util.List;
import java.util.Optional;

/**
 * @author cesarsevilla
 * @project bankingDemoJava
 */

public interface AccountService {
    List<Account> listAccounts();

    Optional<Account> getAccount(Long id);

    Optional<Account> numberAccount(String numAccount);

    Optional<Account> updateAccount(Long id, AccountDto cuenta);

    void insertAccount(Account account);

    boolean getAccountById(Long id);

    void deleteAccount(Long id);
}
