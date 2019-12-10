package com.ayur.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ayur.controller.dto.AppointmentsDTO;
import com.ayur.model.Appointments;
import com.ayur.model.BranchSettings;
import com.ayur.repository.AppointmentRepository;
import com.ayur.repository.BranchSettingsRepository;
import com.paytm.pg.merchant.CheckSumServiceHelper;

@Service
public class AppointmentService extends AbstractService<Appointments, Long> {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private BranchSettingsRepository branchSettingsRepository;

    @Override
    protected JpaRepository<Appointments, Long> getRepository() {
        return appointmentRepository;
    }

    public Appointments findOne(Long id) {
        return appointmentRepository.findOne(id);
    }

    public List<Appointments> findAll() {
        return appointmentRepository.findAll();
    }

    public Appointments save(Appointments appointments) {
        appointments = appointmentRepository.save(appointments);
        BranchSettings branchSettings = branchSettingsRepository.findByBranchAndBookingDate(appointments.getBranch(),
                appointments.getBookingDate());
        branchSettings.setBookingCount(branchSettings.getBookingCount() + 1);
        branchSettingsRepository.save(branchSettings);
        return appointments;
    }

    public String generateAppointmentId(Appointments appointments) {
        LocalDate localDate = LocalDate.now();
        BranchSettings branchSettings = branchSettingsRepository.findByBranchAndBookingDate(appointments.getBranch(),
                appointments.getBookingDate());
        Long bookingCount = branchSettings.getBookingCount() + 1;
        String appointmentId = DateTimeFormatter.ofPattern("yyyyMMdd").format(localDate)
                .concat(appointments.getBranch().getId().toString()).concat(bookingCount.toString());
        System.out.println(appointmentId);
        return appointmentId;
    }

    
    public void generatePaymentLink() throws Exception {
        JSONObject paytmParams = new JSONObject();

        /* body parameters */
        JSONObject body = new JSONObject();

        /* Find your MID in your Paytm Dashboard at https://dashboard.paytm.com/next/apikeys */
        body.put("mid", "wNutke38239007895590");

        /* Possible value are "GENERIC", "FIXED", "INVOICE" */
        body.put("linkType", "FIXED");

        /* Enter your choice of payment link description here, special characters are not allowed */
        body.put("linkDescription", "Ayur Payment");

        /* Enter your choice of payment link name here, special characters and spaces are not allowed */
        body.put("linkName", "AYUR");
        body.put("customerName", "Dinesh");
        body.put("customerMobile", "8106524041");
        body.put("amount", 10);
        body.put("sendSms", true);
        body.put("merchantRequestId", "12345");
        body.put("statusCallbackUrl", "http://localhost:8080/ayur/success");

        /**
         * Generate checksum by parameters we have in body
         * You can get Checksum JAR from https://developer.paytm.com/docs/v1/payment-gateway/#code
         * Find your Merchant Key in your Paytm Dashboard at https://dashboard.paytm.com/next/apikeys
         */
        String checksum = CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum("MQEg&cAp@mKXn2Kz", body.toString());

        /* head parameters */
        JSONObject head = new JSONObject();

        /* This will be AES */
        head.put("tokenType", "AES");

        /* put generated checksum value here */
        head.put("signature", checksum);

        /* prepare JSON string for request */
        paytmParams.put("body", body);
        paytmParams.put("head", head);
        String post_data = paytmParams.toString();

        /* for Staging */
        URL url = new URL("https://securegw-stage.paytm.in/link/create");

        /* for Production */
        // URL url = new URL("https://securegw.paytm.in/link/create);

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            DataOutputStream requestWriter = new DataOutputStream(connection.getOutputStream());
            requestWriter.writeBytes(post_data);
            requestWriter.close();
            String responseData = "";
            InputStream is = connection.getInputStream();
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(is));
            if ((responseData = responseReader.readLine()) != null) {
                System.out.append("Response: " + responseData);
            }
            // System.out.append("Request: " + post_data);
            responseReader.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
