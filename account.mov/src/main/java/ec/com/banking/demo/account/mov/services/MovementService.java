package ec.com.banking.demo.account.mov.services;

import ec.com.banking.demo.account.mov.dtos.DepositWithdrawals;
import ec.com.banking.demo.account.mov.dtos.MovementDto;
import ec.com.banking.demo.account.mov.models.Movement;

import java.util.List;
import java.util.Optional;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

public interface MovementService {
    List<Movement> listMovements();

    Optional<Movement> getMovement(Long id);

    Movement createMovement(MovementDto dto);

    Movement updateMovement(String id, MovementDto movement);

    List<Object[]> listMovements(DepositWithdrawals dto);

    void insertMovement(Movement movement);

    boolean getMovementById(Long id);

    void deleteMovement(Long id);
}
