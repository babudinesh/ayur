package com.ayur.payments;

import java.util.Date;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.ayur.model.Customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@EnableConfigurationProperties
@ConfigurationProperties("patym.payment.sandbox")
public class PaytmDetails {

    private String merchantID;
    private String merchantKey;
    private String channelId;
    private String website;
    private String industryTypeId;
    private String paytmUrl;
    private Map<String,String> details;
}
