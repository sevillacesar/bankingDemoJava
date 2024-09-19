package ec.com.banking.neo.account.client.repositories;

import ec.com.banking.neo.account.client.models.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author cesarsevilla
 * @project bankingDemoJava
 */

@Repository

public interface PersonRepository extends CrudRepository<Person, String> {

}
