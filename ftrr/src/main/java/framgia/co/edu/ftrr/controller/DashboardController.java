package framgia.co.edu.ftrr.controller;

import framgia.co.edu.ftrr.util.ExcelExportUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.Date;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    ExcelExportUtils excelExportUtils;

    @GetMapping("/divstatistic")
    public ResponseEntity<InputStreamResource> exportExcel(@RequestParam("fromDate")
                                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate) {
        try {
            ByteArrayInputStream in = excelExportUtils.exportExcel(fromDate);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=divstatistic.xlsx");
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(new InputStreamResource(in));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
