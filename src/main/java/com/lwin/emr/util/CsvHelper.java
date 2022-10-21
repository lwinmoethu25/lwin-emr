package com.lwin.emr.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import com.lwin.emr.domain.dto.EmrDto;
import com.lwin.emr.exception.FileException;
import com.opencsv.ICSVWriter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CsvHelper {

    public final String CSV_FILE_TYPE = "text/csv";

    public boolean checkCsvFormat(MultipartFile file) {
        return CSV_FILE_TYPE.equals(file.getContentType());
    }

    private String setHeader(Class<EmrDto> clazz) {
        return Arrays.stream( clazz.getDeclaredFields() )
                .filter( f -> f.getAnnotation( CsvBindByPosition.class ) != null
                        && f.getAnnotation( CsvBindByName.class ) != null )
                .sorted( Comparator.comparing( f -> f.getAnnotation( CsvBindByPosition.class ).position() ) )
                .map( f -> f.getAnnotation( CsvBindByName.class ).column() )
                .collect( Collectors.joining( "," ) ) + "\n";
    }

    public List<EmrDto> readCsv(MultipartFile file) throws IOException {
        if (!checkCsvFormat(file)) {
            throw new FileException("Unsupported File.");
        }

        Reader reader = new InputStreamReader(file.getInputStream());

        CsvToBean<EmrDto> csvToBean = new CsvToBeanBuilder(reader)
                .withSkipLines(1)
                .withType(EmrDto.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        return csvToBean.parse();
    }

    public void writeCsv(HttpServletResponse response, List<EmrDto> emrDtoList) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {        
        response.getWriter().append(setHeader( EmrDto.class));
        StatefulBeanToCsv<EmrDto> writer = new StatefulBeanToCsvBuilder<EmrDto>(response.getWriter())
            .withSeparator(ICSVWriter.DEFAULT_SEPARATOR)
            .withOrderedResults(true)
            .build();
        writer.write(emrDtoList);
        response.getWriter().close();
    }
}
