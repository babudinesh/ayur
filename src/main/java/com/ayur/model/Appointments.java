package com.ayur.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Appointments extends AbstractModel<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String name;
	
	@Column
	private Long mobile;
	
	@Column
	private Date bookingDate;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "branch", nullable = false)
	private Branch branch;
	
	@Column
	private String address;
	
	@Column
	private String description;
	
	@Column(name = "dateCreated", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date dateCreated;
	
	@Column
    private String appointmentId;
	
	@Column
	private PaymentStatus paymentStatus;
	
	

}
