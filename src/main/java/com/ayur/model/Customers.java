package com.ayur.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customers extends AbstractModel<Long> {

    @Column(nullable = false, length = 75)
    private String firstname;

    @Column(nullable = false, length = 40)
    private String lastname;

    @Column(nullable = false, unique = true)
    private Long mobile;

    @Column(name = "date_created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private Date dateCreated;

    @Column(nullable = true, length = 500)
    private String address;
}
