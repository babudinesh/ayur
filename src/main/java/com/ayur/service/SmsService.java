package com.ayur.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ayur.model.Appointments;
import com.ayur.model.SmsSettings;
import com.ayur.model.Status;
import com.ayur.payments.PaymentLinkResponse;
import com.ayur.repository.SmsSettingsRepository;

@Service
public class SmsService extends AbstractService<SmsSettings, Long>{

    @Autowired
    private SmsSettingsRepository smsSettingsRepository;
    
    
    public String sendSms(Appointments appointment) {
        
        try {
            Long id = (long) 1;
            SmsSettings smsSettings = smsSettingsRepository.findOne(id);
            if(smsSettings.getStatus().equals(Status.Active)) {
                String message = "Hello " + appointment.getName() + ", Your appointment booked with Ayur on " +appointment.getBookingDate()+ 
                        " at " + appointment.getBranch().getName()+ " branch,Your appointment id: "+ appointment.getAppointmentId()+", Thank You.";
                
                String data = "username=" + URLEncoder.encode(smsSettings.getUsername(), "UTF-8");
                data += "&password=" + (smsSettings.getPassword());
                data += "&to=" + URLEncoder.encode("91"+appointment.getMobile().toString(), "UTF-8");
                data += "&from=" + URLEncoder.encode(smsSettings.getSenderId(), "UTF-8");
                data += "&text=" + URLEncoder.encode(message, "UTF-8");
                //data += "&type=" + URLEncoder.encode("3", "UTF-8");

                System.out.println(smsSettings.getSmsUrl() + data);
                URL url = new URL(smsSettings.getSmsUrl() + data);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();

                // Get the response
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                String sResult = "";
                while ((line = rd.readLine()) != null) {
                    // Process line...
                    sResult = sResult + line + " ";
                }
                wr.close();
                rd.close();
                return sResult;
            }else {
                return null;
            }
            
        } catch (Exception e) {
            System.out.println("Error SMS " + e);
            return "Error " + e;
        }
    }

    @Override
    protected JpaRepository<SmsSettings, Long> getRepository() {
        // TODO Auto-generated method stub
        return smsSettingsRepository;
    }
    
public String sendPaymentNotification(Appointments appointment,PaymentLinkResponse paymentLinkResponse) {
        
        try {
            Long id = (long) 1;
            SmsSettings smsSettings = smsSettingsRepository.findOne(id);
            if(smsSettings.getStatus().equals(Status.Active)) {
                String message = "Hello " + appointment.getName() + ", Your appointment booked successfully, "
                        + "Please pay the amount through the below link. " + paymentLinkResponse.getBody().getShortUrl();
                
                String data = "username=" + URLEncoder.encode(smsSettings.getUsername(), "UTF-8");
                data += "&password=" + URLEncoder.encode(smsSettings.getPassword(), "UTF-8");
                data += "&to=" + URLEncoder.encode("91"+appointment.getMobile().toString(), "UTF-8");
                data += "&from=" + URLEncoder.encode(smsSettings.getSenderId(), "UTF-8");
                data += "&text=" + URLEncoder.encode(message, "UTF-8");
                //data += "&type=" + URLEncoder.encode("3", "UTF-8");

                System.out.println(smsSettings.getSmsUrl() + data);
                URL url = new URL(smsSettings.getSmsUrl() + data);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();

                // Get the response
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                String sResult = "";
                while ((line = rd.readLine()) != null) {
                    // Process line...
                    sResult = sResult + line + " ";
                }
                wr.close();
                rd.close();
                return sResult;
            }else {
                return null;
            }
            
        } catch (Exception e) {
            System.out.println("Error SMS " + e);
            return "Error " + e;
        }
    }
}
