package ec.com.banking.demo.account.client.models;

import lombok.*;
import jakarta.persistence.*;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "clients")
public final class Client extends Person {
    private String password;
    private String status;

    @Builder
    public Client(Long id, String nombre, String genero, int edad, String identificacion, String direccion,
                  String telefono, String password, String status) {
        super(id, nombre, genero, edad, identificacion, direccion, telefono);
        this.password = password;
        this.status = status;
    }
}
