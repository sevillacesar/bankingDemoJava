package ec.com.banking.demo.account.mov.services;

import ec.com.banking.demo.account.mov.models.BackupMovement;

import java.util.List;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

public interface BackupMovementService {

    List<BackupMovement> listBackupMovements();

    void insertBackupMovement(BackupMovement backupMovement);
}
