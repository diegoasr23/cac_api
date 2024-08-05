package etecc.cac.cac_api.service;

import etecc.cac.cac_api.models.PropietarioModel;
import etecc.cac.cac_api.repository.ICAC_Repository_Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CAC_Owner_service {

    @Autowired
    ICAC_Repository_Owner ownerRepo;

    public List<PropietarioModel> getOwners() {
        return ownerRepo.findAll();
    }

    public List<String> getPropertiesID() {
        return ownerRepo.findProperties();
    }
}
