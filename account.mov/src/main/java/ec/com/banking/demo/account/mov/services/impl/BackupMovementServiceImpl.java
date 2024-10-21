package ec.com.banking.demo.account.mov.services.impl;

import ec.com.banking.demo.account.mov.models.BackupMovement;
import ec.com.banking.demo.account.mov.repositories.BackupMovementRepository;
import ec.com.banking.demo.account.mov.services.BackupMovementService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

@Service
public class BackupMovementServiceImpl implements BackupMovementService {

    private final BackupMovementRepository backupMovementRepository;

    public BackupMovementServiceImpl(BackupMovementRepository backupMovementRepository) {
        this.backupMovementRepository = backupMovementRepository;
    }

    @Override
    public List<BackupMovement> listBackupMovements() {
        return (List<BackupMovement>) backupMovementRepository.findAll();
    }

    @Override
    public void insertBackupMovement(BackupMovement backupMovement) {
        backupMovementRepository.save(backupMovement);
    }
}
