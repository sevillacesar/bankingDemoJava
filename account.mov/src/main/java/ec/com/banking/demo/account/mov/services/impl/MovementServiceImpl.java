package ec.com.banking.demo.account.mov.services.impl;

import ec.com.banking.demo.account.mov.dtos.DepositWithdrawals;
import ec.com.banking.demo.account.mov.dtos.MovementDto;
import ec.com.banking.demo.account.mov.mapper.MovementMapper;
import ec.com.banking.demo.account.mov.models.Account;
import ec.com.banking.demo.account.mov.models.BackupMovement;
import ec.com.banking.demo.account.mov.models.Movement;
import ec.com.banking.demo.account.mov.repositories.MovementRepository;
import ec.com.banking.demo.account.mov.services.AccountService;
import ec.com.banking.demo.account.mov.services.BackupMovementService;
import ec.com.banking.demo.account.mov.services.MovementService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final MovementMapper movementMapper;

    public MovementServiceImpl(MovementRepository movementRepository, BackupMovementService backupMovementService,
                               AccountService accountService, MovementMapper movementMapper) {
        this.movementRepository = movementRepository;
        this.backupMovementService = backupMovementService;
        this.accountService = accountService;
        this.movementMapper = movementMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovementDto> listMovements() {
        List<Movement> movements = movementRepository.findAll();
        return movementMapper.movementList2MovementDto(movements);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Movement> getMovement(Long id) {
        return movementRepository.findById(id);
    }

    @Override
    @Transactional
    public MovementDto createMovement(MovementDto dto) {
        Account account = accountService.findByNumberAccount(dto.numberAccount());
        Optional<Movement> movementFromDb = getMovement(account.getId());
        if (movementFromDb.isPresent()) {
            return updateMovement(account.getNumberAccount(), dto);
        } else {
            float balance = account.getInitialBalance() + dto.value();
            if (balance < 0) {
                throw new NoSuchElementException("No existe saldo suficiente");
            }
            Movement movement = new Movement();
            movement.setMovementType(dto.movementType());
            movement.setBalance(balance);
            movement.setStatus(dto.status());
            movement.setAccount(account);
            MovementDto newMovement = insertMovement(movement);

            BackupMovement backupMovement = new BackupMovement();
            backupMovement.setValue(dto.value());
            backupMovement.setDate(new Date());
            backupMovement.setMovementId(movement);
            backupMovement.setBalance(balance);
            backupMovementService.insertBackupMovement(backupMovement);
            return newMovement;
        }
    }

    @Override
    @Transactional
    public MovementDto insertMovement(Movement movement) {
        var newMovement = movementRepository.save(movement);
        return movementMapper.movement2MovementDto(newMovement);
    }

    @Override
    @Transactional
    public MovementDto updateMovement(String numAccount, MovementDto command) {
        Account _account = Optional.ofNullable(accountService.findByNumberAccount(numAccount))
                .orElseThrow(() -> new NoSuchElementException("No se encontró información con el número de cuenta:" + numAccount));

        Movement _movement = movementRepository.findById(_account.getId())
            .orElseThrow(() -> new NoSuchElementException("No se encontró información con el id de cuenta:" + _account.getId()));

        float balance = _movement.getBalance() + command.value();

        if (balance < 0)
            throw new NoSuchElementException("Datos incorrectos,saldo insuficiente.");

        _movement.setBalance(balance);
        _movement.setAccount(_account);
        movementRepository.save(_movement);

        BackupMovement backupMovement = BackupMovement.builder()
                        .value(command.value())
                        .date(new Date())
                        .movementId(_movement)
                        .balance(balance).build();
        backupMovementService.insertBackupMovement(backupMovement);

        return movementMapper.movement2MovementDto(_movement);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> listMovements(DepositWithdrawals dto) {
        return movementRepository.listMovementsReport(dto.client(), dto.date());
    }

    @Override
    @Transactional
    public void deleteMovement(Long id) {
        movementRepository.deleteById(id);
    }
}
