package com.ayur.controller.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.ayur.model.Appointments;
import com.ayur.model.Customers;
import com.ayur.model.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PrescriptionDTO {

    private Long appointmentId;
    private String diagnosis;
    private String description;
    private String nextVisitDate;
    private Integer age;
    private String gender;
    private String dateOfBirth;
    private String height;
    private String weight;
    private String bloodGroup;
    private String sugar;
    private String bloodPressure;
    private String thyroid;
    private String urine;
    private String hungry;
    private String sleep;
    private String motion;
    private String tea;
    private String coffee;
    private String foodType;
    private String productList;
}
