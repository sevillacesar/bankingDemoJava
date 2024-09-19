package ec.com.banking.neo.account.client.dtos;

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
public class UserDto {

    private String nombre;
    private String genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String password;
    private String estado;
}
