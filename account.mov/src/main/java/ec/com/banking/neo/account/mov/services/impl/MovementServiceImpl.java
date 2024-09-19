package ec.com.banking.neo.account.mov.services.impl;

import ec.com.banking.neo.account.mov.dtos.DepositWithdrawals;
import ec.com.banking.neo.account.mov.dtos.MovementDto;
import ec.com.banking.neo.account.mov.models.Account;
import ec.com.banking.neo.account.mov.models.BackupMovement;
import ec.com.banking.neo.account.mov.models.Movement;
import ec.com.banking.neo.account.mov.repositories.AccountRepository;
import ec.com.banking.neo.account.mov.repositories.BackupMovementRepository;
import ec.com.banking.neo.account.mov.repositories.MovementRepository;
import ec.com.banking.neo.account.mov.services.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author cesarsevilla
 * @project bankingDemoJava
 */

@Service
public class MovementServiceImpl implements MovementService {

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private BackupMovementRepository backupMovementRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Movement> listMovements() {
        return (List<Movement>) movementRepository.findAll();
    }

    @Override
    public Optional<Movement> getMovement(Long id) {
        return movementRepository.findById(id);
    }

    @Override
    public void insertMovement(Movement movement) {
        movementRepository.save(movement);
    }

    @Override
    public Optional<Movement> updateMovement(String id, MovementDto command) {
        Optional<Account> cuentaFromDb = accountRepository.findByNumberAccount(id);
        Optional<Movement> movimientoFromDb = Optional.empty();

        if (cuentaFromDb.isPresent()) {
            Account _account = cuentaFromDb.get();
            movimientoFromDb = movementRepository.findById(_account.getId());

            if (movimientoFromDb.isPresent()) {
                Movement _movement = movimientoFromDb.get();

                float balance = _movement.getBalance() + command.getValor();

                if (balance < 0)
                    return Optional.empty();

                _movement.setBalance(balance);
                _movement.setAccountId(_account);
                movementRepository.save(_movement);

                BackupMovement backupMovement = new BackupMovement();
                backupMovement.setValue(command.getValor());
                backupMovement.setDate(new Date());
                backupMovement.setMovementId(_movement);
                backupMovement.setBalance(balance);
                backupMovementRepository.save(backupMovement);

            }
        }
        return movimientoFromDb;
    }

    @Override
    public List<Object[]> listMovements(DepositWithdrawals dto) {
        return movementRepository.listMovementsReport(dto.getClient(), dto.getDate());
    }

    @Override
    public boolean getMovementById(Long id) {
        return movementRepository.findById(id).isPresent();
    }

    @Override
    public void deleteMovement(Long id) {
        movementRepository.deleteById(id);
    }
}
