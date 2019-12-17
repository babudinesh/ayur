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
public class MedicalHistory extends AbstractModel<Long> {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer", nullable = false)
    private Customers customer;
    
    @Column(nullable = true)
    private String bloodGroup;
    
    @Column(nullable = true)
    private String height;
    
    @Column(nullable = true)
    private String weight;
    
    @Column(nullable = true)
    private Check sugar;
    
    @Column(nullable = true)
    private Check bloodPressure;
    
    @Column(nullable = true)
    private Check thyroid;
    
    @Column(nullable = true)
    private Check hungry;
    
    @Column(nullable = true)
    private Check sleep;
    
    @Column(nullable = true)
    private Check motion;
    
    @Column(nullable = true)
    private Check urine;
    
    @Column(nullable = true)
    private Check tea;
    
    @Column(nullable = true)
    private Check coffee;
    
    @Column(nullable = true)
    private FoodType foodType;
    
    @Column(name = "date_created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date dateCreated;
}
