package ec.com.banking.demo.account.mov.services;

import ec.com.banking.demo.account.mov.dtos.AccountReqDto;
import ec.com.banking.demo.account.mov.dtos.AccountDto;
import ec.com.banking.demo.account.mov.models.Account;

import java.util.List;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

public interface AccountService {
    List<AccountDto> listAccounts();

    Account findByNumberAccount(String numAccount);

    AccountDto updateAccount(String id, AccountReqDto cuenta);

    AccountDto insertAccount(AccountReqDto cuenta);

    boolean getAccountById(Long id);

    void deleteAccount(Long id);
}
