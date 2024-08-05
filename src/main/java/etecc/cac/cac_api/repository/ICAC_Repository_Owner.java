package etecc.cac.cac_api.repository;

import etecc.cac.cac_api.models.PropietarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICAC_Repository_Owner extends JpaRepository<PropietarioModel, Long> {

    @Query(value = "SELECT p.num_property FROM PropietarioModel p", nativeQuery = false)
    List<String> findProperties();

    @Query(value = "SELECT COUNT(p.num_property) FROM PropietarioModel p", nativeQuery = false)
    Long countProperties();
}
