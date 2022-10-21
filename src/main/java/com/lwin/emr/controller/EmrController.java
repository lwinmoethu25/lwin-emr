package com.lwin.emr.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lwin.emr.domain.dto.EmrDto;
import com.lwin.emr.service.EmrService;
import com.lwin.emr.util.CsvHelper;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/emr")
@RequiredArgsConstructor
public class EmrController {
    
    private final EmrService emrService;
    private final CsvHelper csvHelper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadData(@RequestParam("file") MultipartFile file) throws IOException {
        List<EmrDto> emrDtoList = csvHelper.readCsv(file);
        emrService.uploadEmr(emrDtoList);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Void> fetchAllData(HttpServletResponse response) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"gerimedica-emr.csv\"");

        try {
            List<EmrDto> emrDtoList = emrService.fetchEmr();
            csvHelper.writeCsv(response, emrDtoList);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> fetchByCode(HttpServletResponse response, @PathVariable("code") String code) throws Exception {
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"gerimedica-emr.csv\"");

        try {
            EmrDto emrDto = emrService.fetchEmrByCode(code);
            csvHelper.writeCsv(response, List.of(emrDto));
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllData() {
        emrService.deleteAllEmr();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
