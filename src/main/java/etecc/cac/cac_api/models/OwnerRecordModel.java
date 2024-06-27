package etecc.cac.cac_api.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cac_access_data")
@Data
public class OwnerRecordModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)")
    private String last_query;

    @Column(name = "propertyID", columnDefinition = "VARCHAR(255)")
    private String propertyID;

    @Column(name = "buildingName", columnDefinition = "VARCHAR(255)")
    private String buildingName;

    @Column(columnDefinition = "VARCHAR(255)")
    private String type;

    @Column(name = "qtyPeople", columnDefinition = "INT")
    private int qtyPeople;
}
