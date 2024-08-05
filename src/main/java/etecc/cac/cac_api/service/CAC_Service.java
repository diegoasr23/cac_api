package etecc.cac.cac_api.service;

import etecc.cac.cac_api.models.OwnerRecordModel;
import etecc.cac.cac_api.repository.ICAC_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class CAC_Service {
    @Autowired
    ICAC_Repository cac_repository;

    public ResponseEntity<String> saveRecord(OwnerRecordModel recordModel) {
        for (Field attributes : recordModel.getClass().getDeclaredFields()) {
            attributes.setAccessible(true);
            if (!attributes.getName().equals("id")) {
                Object value = null;
                try {
                    value = attributes.get(recordModel);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                if (value == null) return ResponseEntity.badRequest().body("Invalid fields");

                if (attributes.getType().equals(String.class)) {
                    if (String.valueOf(value).isEmpty() || String.valueOf(value).isBlank() )
                        return ResponseEntity.badRequest().body("Invalid fields");
                }
            }
        }

        if (recordModel.getQtyPeople() <= 0) {
            return ResponseEntity.badRequest().body("Invalid fields");
        }
        cac_repository.save(recordModel);

        return ResponseEntity.ok("Success");
    }

    public ResponseEntity<String> saveAllRecords(List<OwnerRecordModel> records) {
        cac_repository.saveAll(records);
        return ResponseEntity.ok("Success");
    }
}
