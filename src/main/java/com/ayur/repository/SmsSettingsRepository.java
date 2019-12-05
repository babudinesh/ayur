package com.ayur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayur.model.SmsSettings;

@Repository
public interface SmsSettingsRepository extends JpaRepository<SmsSettings, Long>{

}
