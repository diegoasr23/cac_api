package etecc.cac.cac_api.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "propietarios")
@Data
public class PropietarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "numProperty", columnDefinition = "VARCHAR(45)", nullable = false)
    private String property;

    @Column(name = "state")
    private String state;

    @Column(name = "agreement", columnDefinition = "TINYINT")
    private Boolean agreement;

}
