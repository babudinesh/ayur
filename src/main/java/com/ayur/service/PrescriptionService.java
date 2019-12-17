package com.ayur.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ayur.controller.dto.PrescriptionDTO;
import com.ayur.model.Appointments;
import com.ayur.model.BranchSettings;
import com.ayur.model.Check;
import com.ayur.model.Customers;
import com.ayur.model.FoodType;
import com.ayur.model.Gender;
import com.ayur.model.MedicalHistory;
import com.ayur.model.Prescription;
import com.ayur.repository.AppointmentRepository;
import com.ayur.repository.CustomersRepository;
import com.ayur.repository.PrescriptionRepository;

@Service
public class PrescriptionService extends AbstractService<Prescription, Long>{

    @Autowired
    private PrescriptionRepository prescriptionRepository;
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private CustomersRepository customersRepository;
    
    @Autowired
    private MedicalHistoryService medicalHistoryService;
    @Override
    protected JpaRepository<Prescription, Long> getRepository() {
        // TODO Auto-generated method stub
        return null;
    }

    public Prescription save(PrescriptionDTO prescriptionDTO) throws ParseException {
        Appointments appointments = appointmentRepository.findOne(prescriptionDTO.getAppointmentId());
        Customers customer  = customersRepository.findByMobile(appointments.getMobile());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if(customer==null) {
            customer = new Customers();
            customer.setFirstname(appointments.getName());
            customer.setAddress(appointments.getAddress());
            customer.setMobile(appointments.getMobile());
            customer.setCustomerId(generateCustomerId(appointments));
            customer.setAge(prescriptionDTO.getAge());
            customer.setDateOfBirth(prescriptionDTO.getDateOfBirth().equals("")?null:formatter.parse(prescriptionDTO.getDateOfBirth()));
            customer.setGender(prescriptionDTO.getGender()!=null?Gender.valueOf(prescriptionDTO.getGender()):null);
        }else {
            customer.setAge(prescriptionDTO.getAge());
            customer.setDateOfBirth(prescriptionDTO.getDateOfBirth().equals("")?null:formatter.parse(prescriptionDTO.getDateOfBirth()));
            customer.setGender(prescriptionDTO.getGender()!=null?Gender.valueOf(prescriptionDTO.getGender()):null);
        }
        customer = customersRepository.save(customer);
        MedicalHistory medicalHistory = medicalHistoryService.findByCustomer(customer);
        if(medicalHistory==null) {
            medicalHistory = new MedicalHistory();
            setMedicalHistory(medicalHistory, prescriptionDTO,customer);
        }else {
            setMedicalHistory(medicalHistory, prescriptionDTO,customer);
        }
        medicalHistoryService.save(medicalHistory);
        Prescription prescription = new Prescription();
        prescription.setAppointment(appointments);
        prescription.setCustomer(customer);
        prescription.setDiagnosis(prescriptionDTO.getDiagnosis());
        prescription.setDescription(prescriptionDTO.getDiagnosis());
        prescription.setNextVisitDate(prescriptionDTO.getNextVisitDate().equals("")?null:formatter.parse(prescriptionDTO.getNextVisitDate()));
        prescription.setProducts(prescriptionDTO.getProductList());
        
        return prescriptionRepository.save(prescription);
    }
    
    public String generateCustomerId(Appointments appointments) {
        LocalDate localDate = LocalDate.now();
        String customerId = "AV"+appointments.getBranch().getCode()+DateTimeFormatter.ofPattern("yyyyMMdd").format(localDate)
                .concat(getRandomIntegerBetweenRange(1,1000));
        System.out.println(customerId);
        return customerId;
    }
    
    public static String getRandomIntegerBetweenRange(int min, int max){
        int x = (int)(Math.random()*((max-min)+1))+min;
        return String.valueOf(x);
    }
    
    public List<Prescription> findByCustomer(Customers customer){
        return prescriptionRepository.findByCustomer(customer);
    }
    
    public MedicalHistory setMedicalHistory(MedicalHistory medicalHistory,PrescriptionDTO prescriptionDTO,Customers customer) {
        medicalHistory.setBloodGroup(prescriptionDTO.getBloodGroup());
        medicalHistory.setBloodPressure((prescriptionDTO.getBloodPressure()!=null?Check.Yes:Check.No));
        medicalHistory.setCoffee((prescriptionDTO.getCoffee()!=null?Check.Yes:Check.No));
        medicalHistory.setCustomer(customer);
        medicalHistory.setFoodType(prescriptionDTO.getFoodType()!=null?FoodType.valueOf(prescriptionDTO.getFoodType()):null);
        medicalHistory.setHeight((prescriptionDTO.getHeight()));
        medicalHistory.setHungry((prescriptionDTO.getHungry()!=null?Check.Yes:Check.No));
        medicalHistory.setMotion((prescriptionDTO.getMotion()!=null?Check.Yes:Check.No));
        medicalHistory.setSleep((prescriptionDTO.getSleep()!=null?Check.Yes:Check.No));
        medicalHistory.setSugar((prescriptionDTO.getSugar()!=null?Check.Yes:Check.No));
        medicalHistory.setTea((prescriptionDTO.getTea()!=null?Check.Yes:Check.No));
        medicalHistory.setThyroid((prescriptionDTO.getThyroid()!=null?Check.Yes:Check.No));
        medicalHistory.setUrine((prescriptionDTO.getUrine()!=null?Check.Yes:Check.No));
        medicalHistory.setWeight((prescriptionDTO.getWeight()));
        return medicalHistory;
    }
}
