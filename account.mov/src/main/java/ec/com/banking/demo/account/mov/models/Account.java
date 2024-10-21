package ec.com.banking.demo.account.mov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "number_account")
    private String numberAccount;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "initial_balance")
    private Float initialBalance;

    private String status;

    private String client;
}
