package etecc.cac.cac_api.controller;

import etecc.cac.cac_api.error.LogFile;
import etecc.cac.cac_api.models.OwnerRecordModel;
import etecc.cac.cac_api.models.PropietarioModel;
import etecc.cac.cac_api.service.CAC_Owner_service;
import etecc.cac.cac_api.service.CAC_Service;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/etecc/cac/")
public class CAC_Controller {

    @Autowired
    private final CAC_Service service;

    @Autowired
    private final CAC_Owner_service cacOwnerService;

    private List<String> ownerList;

    public CAC_Controller(CAC_Service service, CAC_Owner_service cacOwnerService) {
        LogFile.init();
        this.service = service;
        this.cacOwnerService = cacOwnerService;
        ownerList = cacOwnerService.getPropertiesID();
    }

    @PostMapping("/save")
    public ResponseEntity saveRecord(@RequestBody OwnerRecordModel recordModel, HttpServletRequest request) {

        if (!ownerList.contains(recordModel.getPropertyID())) {
            return ResponseEntity.badRequest().body("NOT A VALID PROPERTY ID");
        }
        service.saveRecord(recordModel);
        String action = new String("CLIENT INFO: " + getClientMetaData(request) + " \n " + recordModel.toString());
        LogFile.writeLogAction(action);
        return ResponseEntity.ok().body("SUCCESS OPERATION");
    }

     public static String getClientMetaData(@NonNull HttpServletRequest request) {
        String metadataStr="";

        Iterator<String> it_headerNames = request.getHeaderNames().asIterator();
        while (it_headerNames.hasNext()) {
            String header = it_headerNames.next();
            String value = request.getHeader(header);
            metadataStr +=  header + " : " + value + "\n" ;
        }

        return "{\n" + metadataStr + "}";
    }

    @PostMapping("/saveAll")
    public ResponseEntity saveRecord(@RequestBody List<OwnerRecordModel> recordModel) {

        for (OwnerRecordModel record : recordModel) {
            if (!ownerList.contains(record.getPropertyID())) {
                return ResponseEntity.badRequest().body("THERE ISN'T NOT A VALID PROPERTY ID "
                        + record.getPropertyID());
            }
        }

        StringBuilder sb = new StringBuilder();
        recordModel.forEach(r -> sb.append(r.toString()).append("\n"));
        LogFile.writeLogAction(sb.toString());
        return service.saveAllRecords(recordModel);
    }

    @GetMapping("/owners")
    public List<PropietarioModel> getOwners(HttpServletRequest request) {
        String action = new String("GET OWNERS : CLIENT INFO: " + getClientMetaData(request));
        LogFile.writeLogAction(action);
        return cacOwnerService.getOwners();
    }

    @GetMapping("/home")
    public String home() {
        return "Hello World from server!";
    }
}
