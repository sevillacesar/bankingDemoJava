package ec.com.banking.demo.account.mov.repositories;

import ec.com.banking.demo.account.mov.models.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

  @Query(value = """
      SELECT DATE_FORMAT(h.`date`,'%d/%m/%Y') AS date,
             c.client,
             c.number_account,
             c.account_type,
             c.initial_balance,
             c.status,
             h.value as movement,
             m.balance
      FROM accounts c
      JOIN movements m ON c.id = m.account_id
      JOIN backup h ON m.id = h.movement_id
      WHERE c.client = :client
        AND DATE_FORMAT(h.`date`, '%d-%m-%Y') = :date""", nativeQuery = true)
  List<Object[]> listMovementsReport(String client, String date);
}
