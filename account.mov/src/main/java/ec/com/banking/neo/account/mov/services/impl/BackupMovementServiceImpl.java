package ec.com.banking.neo.account.mov.services.impl;

import ec.com.banking.neo.account.mov.models.BackupMovement;
import ec.com.banking.neo.account.mov.repositories.BackupMovementRepository;
import ec.com.banking.neo.account.mov.services.BackupMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cesarsevilla
 * @project bankingDemoJava
 */

@Service
public class BackupMovementServiceImpl implements BackupMovementService {

    @Autowired
    private BackupMovementRepository backupMovementRepository;

    @Override
    public List<BackupMovement> listBackupMovements() {
        return (List<BackupMovement>) backupMovementRepository.findAll();
    }

    @Override
    public void insertBackupMovement(BackupMovement backupMovement) {
        backupMovementRepository.save(backupMovement);
    }
}
