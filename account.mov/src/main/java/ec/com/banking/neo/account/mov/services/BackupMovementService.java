package ec.com.banking.neo.account.mov.services;

import ec.com.banking.neo.account.mov.models.BackupMovement;

import java.util.List;

/**
 * @author cesarsevilla
 * @project bankingDemoJava
 */

public interface BackupMovementService {

    List<BackupMovement> listBackupMovements();

    void insertBackupMovement(BackupMovement backupMovement);
}
