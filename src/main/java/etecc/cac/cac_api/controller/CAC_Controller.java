package etecc.cac.cac_api.controller;

import etecc.cac.cac_api.models.OwnerRecordModel;
import etecc.cac.cac_api.models.PropietarioModel;
import etecc.cac.cac_api.service.CAC_Owner_service;
import etecc.cac.cac_api.service.CAC_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etecc/cac/")
public class CAC_Controller {

    @Autowired
    private CAC_Service service;

    @Autowired
    private CAC_Owner_service cacOwnerService;

    @PostMapping("/save")
    public ResponseEntity saveRecord(@RequestBody OwnerRecordModel recordModel) {
        return service.saveRecord(recordModel);
    }

    @PostMapping("/saveAll")
    public ResponseEntity saveRecord(@RequestBody List<OwnerRecordModel> recordModel) {

        return service.saveAllRecords(recordModel);
    }

    @GetMapping("/owners")
    public ResponseEntity<List<PropietarioModel>> getOwners() {
        return cacOwnerService.getOwners();
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Hello World! from server";
    }
}
