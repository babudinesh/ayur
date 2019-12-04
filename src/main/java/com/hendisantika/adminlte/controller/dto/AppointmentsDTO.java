package com.hendisantika.adminlte.controller.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class AppointmentsDTO {

    private String name;
    
    private Long mobile;
    
    private Date bookingDate;
    
    private Long branch;
    
    private String address;
    
    private String description;
}
