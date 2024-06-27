package etecc.cac.cac_api.repository;

import etecc.cac.cac_api.models.PropietarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICAC_Repository_Owner extends JpaRepository<PropietarioModel, Long> {
}
