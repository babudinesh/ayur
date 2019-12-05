package com.ayur.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

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
public class SmsSettings extends AbstractModel<Long>{

    private static final long serialVersionUID = 1L;

    @Column
    private String smsUrl;
    
    @Column
    private String username;
    
    @Column
    private String password;
    
    @Column
    private String senderId;
    
    @Column
    private Status status;
    
    
}
