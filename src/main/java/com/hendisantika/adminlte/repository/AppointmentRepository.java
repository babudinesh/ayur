package com.hendisantika.adminlte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hendisantika.adminlte.model.Appointments;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointments, Long>{

}
