package ec.com.banking.demo.account.client.dtos;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */
public record ClientDto(String nombre, String genero, int edad, String identificacion, String direccion, String telefono,
                        String password, String estado) {
}
