package ec.com.banking.demo.account.mov.services.impl;

import ec.com.banking.demo.account.mov.dtos.AccountDto;
import ec.com.banking.demo.account.mov.models.Account;
import ec.com.banking.demo.account.mov.repositories.AccountRepository;
import ec.com.banking.demo.account.mov.services.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final KafkaConsumerService kafkaConsumerService;

    public AccountServiceImpl(AccountRepository accountRepository, KafkaConsumerService kafkaConsumerService) {
        this.accountRepository = accountRepository;
        this.kafkaConsumerService = kafkaConsumerService;
    }

    @Override
    public List<Account> listAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account findByNumberAccount(String numAccount) {
        return accountRepository.findByNumberAccount(numAccount);
    }

    @Override
    public void insertAccount(AccountDto cuenta) {
        Long clientId = kafkaConsumerService.getDataByNameClient(cuenta.nameClient());
        Optional<Account> temp = Optional.ofNullable(findByNumberAccount(cuenta.numberAccount()));
        if (temp.isPresent()) {
            throw new NoSuchElementException("El numero de cuenta ya existe");
        }
        if (cuenta.initialBalance() <= 0.0f) {
            throw new NoSuchElementException("El saldo inicial debe ser positivo");
        }
        Account account = Account.builder()
                .numberAccount(cuenta.numberAccount())
                .accountType(cuenta.accountType())
                .initialBalance(cuenta.initialBalance())
                .status(cuenta.status())
                .client(cuenta.nameClient())
                .build();
        accountRepository.save(account);
    }

    @Override
    public Account updateAccount(String numAccount, AccountDto cuenta) {
        Account _account = Optional.ofNullable(accountRepository.findByNumberAccount(numAccount))
                .orElseThrow(() -> new NoSuchElementException("No se encontró información con el número de cuenta:" + numAccount));
        _account.setNumberAccount(cuenta.numberAccount());
        _account.setAccountType(cuenta.accountType());
        _account.setInitialBalance(cuenta.initialBalance());
        _account.setClient(cuenta.nameClient());
        _account.setStatus(cuenta.status());
        return accountRepository.save(_account);
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
