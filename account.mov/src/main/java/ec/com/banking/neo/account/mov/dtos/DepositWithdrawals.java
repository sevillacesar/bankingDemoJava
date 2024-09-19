package ec.com.banking.neo.account.mov.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cesarsevilla
 * @project bankingDemoJava
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class DepositWithdrawals {

    private String client;
    private String date;
}
