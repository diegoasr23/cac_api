package etecc.cac.cac_api.controller;

import etecc.cac.cac_api.models.OwnerRecordModel;
import etecc.cac.cac_api.service.CAC_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/cac")
public class CAC_Controller {

    @Autowired
    private CAC_Service service;

    @PostMapping("/save")
    public ResponseEntity saveRecord(@RequestBody OwnerRecordModel recordModel) {
        return service.saveRecord(recordModel);
    }

    @PostMapping("/saveAll")
    public ResponseEntity saveRecord(@RequestBody List<OwnerRecordModel> recordModel) {

        return service.saveAllRecords(recordModel);
    }
}
