package etecc.cac.cac_api.controller;

import etecc.cac.cac_api.models.OwnerRecordModel;
import etecc.cac.cac_api.models.PropietarioModel;
import etecc.cac.cac_api.service.CAC_Owner_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cac")
public class CAC_Owner_Controller {

    @Autowired
    private CAC_Owner_service cacOwnerService;

    @GetMapping("/owners")
    public ResponseEntity<List<PropietarioModel>> getOwners() {
        return cacOwnerService.getOwners();
    }
}
