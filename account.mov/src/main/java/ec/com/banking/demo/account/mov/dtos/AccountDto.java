package ec.com.banking.demo.account.mov.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */
@JsonSerialize
public record AccountDto(
        @NotNull @JsonProperty("number_account") String numberAccount,
        @NotNull @JsonProperty("account_type") String accountType,
        @NotNull @JsonProperty("initial_balance") Float initialBalance,
        @NotNull String status,
        @NotNull @JsonProperty("name_client") String nameClient
) {}
