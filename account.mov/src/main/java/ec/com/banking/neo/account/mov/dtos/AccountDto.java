package ec.com.banking.neo.account.mov.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author cesarsevilla
 * @project bankingDemoJava
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class AccountDto {

    @NotNull
    @JsonProperty("number_account")
    private String numberAccount;

    @NotNull
    @JsonProperty("account_type")
    private String AccountType;

    @NotNull
    @JsonProperty("initial_balance")
    private Float InitialBalance;

    @NotNull
    private String status;

    @NotNull
    @JsonProperty("name_client")
    private String nameClient;
}
