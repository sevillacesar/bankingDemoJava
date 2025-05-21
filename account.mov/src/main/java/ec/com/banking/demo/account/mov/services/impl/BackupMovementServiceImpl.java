package ec.com.banking.demo.account.mov.services.impl;

import ec.com.banking.demo.account.mov.models.BackupMovement;
import ec.com.banking.demo.account.mov.repositories.BackupMovementRepository;
import ec.com.banking.demo.account.mov.services.BackupMovementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public List<BackupMovement> listBackupMovements() {
        return (List<BackupMovement>) backupMovementRepository.findAll();
    }

    @Override
    @Transactional
    public void insertBackupMovement(BackupMovement backupMovement) {
        backupMovementRepository.save(backupMovement);
    }
}
