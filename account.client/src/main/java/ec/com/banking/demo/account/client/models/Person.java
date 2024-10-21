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
@MappedSuperclass
public sealed class Person permits Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nombre;
    protected String genero;
    protected int edad;
    protected String identificacion;
    protected String direccion;
    protected String telefono;
}
