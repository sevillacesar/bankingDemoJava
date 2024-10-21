package ec.com.banking.demo.account.client.dtos;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */
public record UserDto(String nombre, String genero, int edad, String identificacion, String direccion, String telefono,
        String password, String estado) {
}
