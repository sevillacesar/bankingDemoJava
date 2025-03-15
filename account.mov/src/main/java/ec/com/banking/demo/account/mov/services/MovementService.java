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
    List<MovementDto> listMovements();

    Optional<Movement> getMovement(Long id);

    MovementDto createMovement(MovementDto dto);

    MovementDto updateMovement(String id, MovementDto movement);

    List<Object[]> listMovements(DepositWithdrawals dto);

    MovementDto insertMovement(Movement movement);

    void deleteMovement(Long id);
}
