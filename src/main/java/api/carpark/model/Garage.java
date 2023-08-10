package api.carpark.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private int capacity;
    private boolean acceptsLpg;
    @OneToMany
    private Set<Car> cars;
    @Version
    private int version;
}
