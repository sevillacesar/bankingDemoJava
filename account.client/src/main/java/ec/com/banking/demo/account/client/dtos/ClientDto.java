package ec.com.banking.demo.account.client.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */
public record ClientDto(
        @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
        String nombre,
        @NotNull(message = "El género es obligatorio")
        String genero,
        @NotNull
        @Min(value = 18, message = "La edad debe ser mayor o igual a 18")
        Integer edad,
        @NotNull String identificacion,
        String direccion,
        String telefono,
        String password,
        @NotNull String estado) { }
