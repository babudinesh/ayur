package com.ayur.payments;

import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ayur.model.Appointments;
import com.ayur.model.PaymentStatus;
import com.ayur.repository.AppointmentRepository;
import com.ayur.service.SmsService;
import com.paytm.pg.merchant.CheckSumServiceHelper;

@Controller
public class PaytmPaymentController {

    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private SmsService smsService;
    
    @GetMapping("/pgredirect")
    public ModelAndView getRedirect(
            @RequestParam("orderId") String orderId,
            @RequestParam("custId") String customerId,
            @RequestParam("mobile") String mobile,HttpServletRequest request) throws Exception {
        
        ModelAndView modelAndView = new ModelAndView("redirect: https://securegw-stage.paytm.in/order/process");
        TreeMap<String, String> parameters = new TreeMap<>();
        //paytmDetails.getDetails().forEach((k,v) -> parameters.put(k, v));
        parameters.put("MID", "wNutke38239007895590");
        parameters.put("CHANNEL_ID", "WEB");
        parameters.put("INDUSTRY_TYPE_ID", "Retail");
        parameters.put("WEBSITE", "WEBSTAGING");
        parameters.put("CALLBACK_URL", "http://"+request.getServerName()+":"+request.getServerPort()+""+request.getContextPath()+"/payment-callback?custId="+customerId);
        parameters.put("MOBILE_NO", mobile);
        //parameters.put("EMAIL", "babudienshkumar@gmail.com");
        parameters.put("ORDER_ID", orderId);
        parameters.put("TXN_AMOUNT", "10");
        parameters.put("CUST_ID", customerId);
        String checksum = getCheckSum(parameters);
        parameters.put("CHECKSUMHASH", checksum);
        modelAndView.addAllObjects(parameters);
        return modelAndView;
    }

    private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
        // TODO Auto-generated method stub
        return CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum("MQEg&cAp@mKXn2Kz", parameters);
    }
    
    @RequestMapping(value="/payment-callback")
    public ModelAndView paymentSuccess(@RequestParam("custId") Long appointmentId, HttpServletRequest request,HttpServletResponse response) {
        String message ;
        String appId = "0";
        if(request.getParameter("STATUS").equalsIgnoreCase("TXN_SUCCESS")) {
            Appointments appointment = appointmentRepository.findOne(appointmentId);
            appointment.setPaymentStatus(PaymentStatus.Done);
            appId = appointment.getAppointmentId();
            appointmentRepository.save(appointment);
            smsService.sendSms(appointment);
            message = "Appointment booked successfully";
        }else {
            message = "Sorry cannot book appointment !..";
        }
        System.out.println(request.getParameter("STATUS"));
        System.out.println(request.getParameter("RESPMSG"));
        ModelAndView modelAndView = new ModelAndView("redirect: http://"+request.getServerName()+":"+request.getServerPort()+"/ayurvedic?message="+message+"&appointmentId="
                + ""+appId);
        return modelAndView;
        
    }
}
