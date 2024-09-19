package ec.com.banking.neo.account.mov.services.impl;

import ec.com.banking.neo.account.mov.dtos.AccountDto;
import ec.com.banking.neo.account.mov.models.Account;
import ec.com.banking.neo.account.mov.repositories.AccountRepository;
import ec.com.banking.neo.account.mov.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author cesarsevilla
 * @project bankingDemoJava
 */

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> listAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Optional<Account> numberAccount(String numAccount) {
        return accountRepository.findByNumberAccount(numAccount);
    }

    @Override
    public void insertAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Optional<Account> updateAccount(Long id, AccountDto cuenta) {
        Optional<Account> cuentaFromDb = accountRepository.findByNumberAccount(id.toString());

        if (cuentaFromDb.isPresent()) {
            Account _account = cuentaFromDb.get();
            _account.setNumberAccount(cuenta.getNumberAccount());
            _account.setAccountType(cuenta.getAccountType());
            _account.setInitialBalance(cuenta.getInitialBalance());
            _account.setClient(cuenta.getNameClient());
            _account.setStatus(cuenta.getStatus());
            accountRepository.save(_account);
        }
        return cuentaFromDb;
    }

    @Override
    public boolean getAccountById(Long id) {
        return accountRepository.findById(id).isPresent();
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
