package ec.com.banking.demo.account.mov.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */
@JsonSerialize
public record ReporteDto(
        String fecha,
        String cliente,
        String numeroCuenta,
        String tipoCuenta,
        String saldoInicial,
        String estado,
        String movimiento,
        String saldo
) {}
