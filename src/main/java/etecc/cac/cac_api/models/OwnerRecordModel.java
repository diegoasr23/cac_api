package etecc.cac.cac_api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "cac_access_data")
public class OwnerRecordModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public String toString() {
        return "OwnerRecordModel{" +
                "id=" + id +
                ", last_query='" + last_query + '\'' +
                ", propertyID='" + propertyID + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", type='" + type + '\'' +
                ", qtyPeople=" + qtyPeople +
                '}';
    }

    public String getLast_query() {
        return last_query;
    }

    public String getPropertyID() {
        return propertyID;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public String getType() {
        return type;
    }

    public int getQtyPeople() {
        return qtyPeople;
    }

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
