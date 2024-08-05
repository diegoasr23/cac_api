package etecc.cac.cac_api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "propietarios")
@Data
public class PropietarioModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "num_property", columnDefinition = "VARCHAR(45)", nullable = false)
    private String num_property;

    @Column(name = "state")
    private String state;

    @Column(name = "agreement", columnDefinition = "TINYINT")
    private Boolean agreement;

}
