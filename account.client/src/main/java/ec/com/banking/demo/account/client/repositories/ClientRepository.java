package ec.com.banking.demo.account.client.repositories;

import ec.com.banking.demo.account.client.models.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
      @Query(value = "select id from clients where nombre = ?1", nativeQuery = true)
      Optional<Long> findByNombre(String name);
}