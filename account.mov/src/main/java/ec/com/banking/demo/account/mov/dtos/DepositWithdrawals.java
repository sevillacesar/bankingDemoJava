package ec.com.banking.demo.account.mov.dtos;

import java.io.Serializable;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

public record DepositWithdrawals(String client, String date) implements Serializable {}
