package com.ayur.payments;

import java.util.Date;

import com.ayur.model.Branch;
import com.ayur.model.PaymentStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseHead {

    private String version;
    private String timestamp;
    private String channelId;
    private String signature;
    private String tokenType;
    private String clientId;
}
