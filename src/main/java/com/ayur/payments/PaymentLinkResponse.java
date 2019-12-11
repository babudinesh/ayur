package com.ayur.payments;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentLinkResponse {

    private ResponseHead head;
    private ResponseBody body;
}
