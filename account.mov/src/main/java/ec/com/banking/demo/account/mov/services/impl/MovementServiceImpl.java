package ec.com.banking.demo.account.mov.services.impl;

import ec.com.banking.demo.account.mov.dtos.DepositWithdrawals;
import ec.com.banking.demo.account.mov.dtos.MovementDto;
import ec.com.banking.demo.account.mov.models.Account;
import ec.com.banking.demo.account.mov.models.BackupMovement;
import ec.com.banking.demo.account.mov.models.Movement;
import ec.com.banking.demo.account.mov.repositories.MovementRepository;
import ec.com.banking.demo.account.mov.services.AccountService;
import ec.com.banking.demo.account.mov.services.BackupMovementService;
import ec.com.banking.demo.account.mov.services.MovementService;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

@Service
public class MovementServiceImpl implements MovementService {

    private final MovementRepository movementRepository;

    private final BackupMovementService backupMovementService;

    private final AccountService accountService;

    public MovementServiceImpl(MovementRepository movementRepository, BackupMovementService backupMovementService, AccountService accountService) {
        this.movementRepository = movementRepository;
        this.backupMovementService = backupMovementService;
        this.accountService = accountService;
    }

    @Override
    public List<Movement> listMovements() {
        return (List<Movement>) movementRepository.findAll();
    }

    @Override
    public Optional<Movement> getMovement(Long id) {
        return movementRepository.findById(id);
    }

    @Override
    public Movement createMovement(MovementDto dto) {
        Account account = accountService.findByNumberAccount(dto.numeroCuenta());
        Optional<Movement> movementFromDb = getMovement(account.getId());
        if (movementFromDb.isPresent()) {
            return updateMovement(account.getNumberAccount(), dto);
        } else {
            float balance = account.getInitialBalance() + dto.valor();
            if (balance < 0) {
                throw new NoSuchElementException("No existe saldo suficiente");
            }
            Movement movement = new Movement();
            movement.setMovementType(dto.tipoMovimiento());
            movement.setBalance(balance);
            movement.setStatus(dto.estado());
            movement.setAccountId(account);
            insertMovement(movement);

            BackupMovement backupMovement = new BackupMovement();
            backupMovement.setValue(dto.valor());
            backupMovement.setDate(new Date());
            backupMovement.setMovementId(movement);
            backupMovement.setBalance(balance);
            backupMovementService.insertBackupMovement(backupMovement);
            return movement;
        }
    }

    @Override
    public void insertMovement(Movement movement) {
        movementRepository.save(movement);
    }

    @Override
    public Movement updateMovement(String numAccount, MovementDto command) {
        Account _account = Optional.ofNullable(accountService.findByNumberAccount(numAccount))
                .orElseThrow(() -> new NoSuchElementException("No se encontró información con el número de cuenta:" + numAccount));

        Movement _movement = movementRepository.findById(_account.getId())
            .orElseThrow(() -> new NoSuchElementException("No se encontró información con el id de cuenta:" + _account.getId()));

        float balance = _movement.getBalance() + command.valor();

        if (balance < 0)
            throw new NoSuchElementException("Datos incorrectos,saldo insuficiente.");

        _movement.setBalance(balance);
        _movement.setAccountId(_account);
        movementRepository.save(_movement);

        BackupMovement backupMovement = new BackupMovement();
        backupMovement.setValue(command.valor());
        backupMovement.setDate(new Date());
        backupMovement.setMovementId(_movement);
        backupMovement.setBalance(balance);
        backupMovementService.insertBackupMovement(backupMovement);

        return _movement;
    }

    @Override
    public List<Object[]> listMovements(DepositWithdrawals dto) {
        return movementRepository.listMovementsReport(dto.client(), dto.date());
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
