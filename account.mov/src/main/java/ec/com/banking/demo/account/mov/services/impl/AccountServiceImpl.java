package ec.com.banking.demo.account.mov.services.impl;

import ec.com.banking.demo.account.mov.dtos.AccountReqDto;
import ec.com.banking.demo.account.mov.dtos.AccountDto;
import ec.com.banking.demo.account.mov.mapper.AccountMapper;
import ec.com.banking.demo.account.mov.models.Account;
import ec.com.banking.demo.account.mov.repositories.AccountRepository;
import ec.com.banking.demo.account.mov.services.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, KafkaConsumerService kafkaConsumerService, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.kafkaConsumerService = kafkaConsumerService;
        this.accountMapper = accountMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountDto> listAccounts() {
        Iterable<Account> accounts = this.accountRepository.findAll();
        return accountMapper.accountsToAccountDtos(accounts);
    }

    @Override
    @Transactional(readOnly = true)
    public Account findByNumberAccount(String numAccount) {
        return accountRepository.findByNumberAccount(numAccount);
    }

    @Override
    @Transactional
    public AccountDto insertAccount(AccountReqDto cuenta) {
        Long clientId = kafkaConsumerService.getDataByNameClient(cuenta.nameClient());
        Optional.ofNullable(findByNumberAccount(cuenta.numberAccount()))
                .orElseThrow(() -> new NoSuchElementException("El numero de cuenta ya existe"));
        if (cuenta.initialBalance() <= 0.0f) {
            throw new NoSuchElementException("El saldo inicial debe ser positivo");
        }
        Account accountToSave = accountMapper.accountReqDtoToAccount(cuenta);
        accountToSave.setClientId(clientId);
        Account newAccount = accountRepository.save(accountToSave);
        return accountMapper.accountToAccountDto(newAccount);
    }

    @Override
    @Transactional
    public AccountDto updateAccount(String numAccount, AccountReqDto cuenta) {
        Account _account = Optional.ofNullable(accountRepository.findByNumberAccount(numAccount))
                .orElseThrow(() -> new NoSuchElementException("No se encontró información con el número de cuenta:" + numAccount));
        Long clientId = kafkaConsumerService.getDataByNameClient(cuenta.nameClient());
        accountMapper.updateAccountFromDto(cuenta, _account);
        _account.setClientId(clientId);
        Account account = accountRepository.save(_account);
        return accountMapper.accountToAccountDto(account);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean getAccountById(Long id) {
        return accountRepository.findById(id).isPresent();
    }

    @Override
    @Transactional
    public void deleteAccount(Long id) {
        accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontró información de la cuenta id:" + id));
        accountRepository.deleteById(id);
    }
}
