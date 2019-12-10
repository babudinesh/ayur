package com.ayur.payments;

import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.paytm.pg.merchant.CheckSumServiceHelper;

@Controller
public class PaytmPaymentController {

    @PostMapping("/pgredirect")
    public ModelAndView getRedirect() throws Exception {
        
        ModelAndView modelAndView = new ModelAndView("redirect: https://securegw-stage.paytm.in/order/process");
        TreeMap<String, String> parameters = new TreeMap<>();
        //paytmDetails.getDetails().forEach((k,v) -> parameters.put(k, v));
        parameters.put("MID", "wNutke38239007895590");
        parameters.put("CHANNEL_ID", "WEB");
        parameters.put("INDUSTRY_TYPE_ID", "Retail");
        parameters.put("WEBSITE", "WEBSTAGING");
        parameters.put("CALLBACK_URL", "http://localhost:8080/ayur/success");
        parameters.put("MOBILE_NO", "8106524041");
        parameters.put("EMAIL", "babudienshkumar@gmail.com");
        parameters.put("ORDER_ID", "TXR125");
        parameters.put("TXN_AMOUNT", "10");
        parameters.put("CUST_ID", "321321");
        String checksum = getCheckSum(parameters);
        parameters.put("CHECKSUMHASH", checksum);
        modelAndView.addAllObjects(parameters);
        return modelAndView;
    }

    private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
        // TODO Auto-generated method stub
        return CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum("MQEg&cAp@mKXn2Kz", parameters);
    }
}
