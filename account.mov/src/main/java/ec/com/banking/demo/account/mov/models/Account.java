package ec.com.banking.demo.account.mov.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.List;

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

    @Column(name = "client_id")
    private Long clientId;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("account")
    private List<Movement> movements;
}
