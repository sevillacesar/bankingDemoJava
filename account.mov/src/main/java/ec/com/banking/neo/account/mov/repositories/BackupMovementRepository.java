package ec.com.banking.neo.account.mov.repositories;

import ec.com.banking.neo.account.mov.models.BackupMovement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author cesarsevilla
 * @project bankingDemoJava
 */

@Repository
public interface BackupMovementRepository extends CrudRepository<BackupMovement, Long> {
}
