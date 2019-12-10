package com.ayur.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Prescription extends AbstractModel<Long>{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "appointment", nullable = false)
    private Appointments appointment;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer", nullable = false)
    private Customers customer;
    
    @Column(nullable = false, length = 500)
    private String diagnosis;
    
    @Column(nullable = false, length = 500)
    private String description;
    
    @Column(nullable = true)
    private Date nextVisitDate;
    
    @Column(name = "dateCreated", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date dateCreated;
}
