package ec.com.banking.demo.account.mov.repositories;

import ec.com.banking.demo.account.mov.models.BackupMovement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

@Repository
public interface BackupMovementRepository extends CrudRepository<BackupMovement, Long> {
}
