package com.example.officebuilding.dtos;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CompanyDTO {
    private int id;
    private String cusName;
    private String cusEmail;
    private String cusPhone;
    private String cusTaxCode;
    private int cusStatus;
    private Timestamp cTime;
}
