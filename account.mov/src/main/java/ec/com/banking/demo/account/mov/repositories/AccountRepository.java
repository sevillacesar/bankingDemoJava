package ec.com.banking.demo.account.mov.repositories;

import ec.com.banking.demo.account.mov.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByNumberAccount(String numAccount);
}
