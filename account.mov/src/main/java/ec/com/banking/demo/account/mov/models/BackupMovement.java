package ec.com.banking.demo.account.mov.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import java.util.Date;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "backup")
public class BackupMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private Float value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movement_id")
    @JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
    private Movement movementId;

    private Float balance;
}
