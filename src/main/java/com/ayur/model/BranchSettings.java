package com.ayur.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

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
public class BranchSettings extends AbstractModel<Long>{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "branch", nullable = false)
    private Branch branch;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column
    private Date bookingDate;
    
    @Column
    private Status status;
    
    @Column
    private Long appointmentCount;
    
    @Column(name = "dateCreated", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date dateCreated;
}
