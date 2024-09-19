package ec.com.banking.neo.account.client.repositories;

import ec.com.banking.neo.account.client.models.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author cesarsevilla
 * @project bankingDemoJava
 */

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
      @Query(value = "select a.id from clients a, persons p where a.person_id = p.id and p.nombre = ?1", nativeQuery = true)
      int findByNombre(String name);
}