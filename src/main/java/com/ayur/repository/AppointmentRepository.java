package com.ayur.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ayur.model.Appointments;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointments, Long>{

    @Query(value="Select appointment_id,name,mobile from appointments where booking_date between ?1 and ?2 ",nativeQuery=true)
    List<Object[]> findByAppointmentDates(String fromDate,String toDate);
}
