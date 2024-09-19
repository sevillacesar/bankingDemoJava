package ec.com.banking.neo.account.mov.repositories;

import ec.com.banking.neo.account.mov.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author cesarsevilla
 * @project bankingDemoJava
 */

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByNumberAccount(String numAccount);
}
