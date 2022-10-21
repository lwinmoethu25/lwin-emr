package com.lwin.emr.domain.dto;

import java.util.Date;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmrDto {

    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "source")
    private String source;

    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "codeListCode")
    private String codeListCode;

    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "code")
    private String code;

    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "displayValue")
    private String displayValue;

    @CsvBindByPosition(position = 4)
    @CsvBindByName(column = "longDescription")
    private String longDescription;

    @CsvBindByPosition(position = 5)
    @CsvBindByName(column = "fromDate")
    @CsvDate(value = "dd-MM-yyyy")
    private Date fromDate;

    @CsvBindByPosition(position = 6)
    @CsvBindByName(column = "toDate")
    @CsvDate(value = "dd-MM-yyyy")
    private Date toDate;

    @CsvBindByPosition(position = 7)
    @CsvBindByName(column = "sortingPriority")
    private Integer sortingPriority;
}
