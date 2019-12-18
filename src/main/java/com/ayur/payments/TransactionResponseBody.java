package com.ayur.payments;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionResponseBody {

    private ResultInfo resultInfo;
    private String merchantId;
    private String merchantName;
    private List<TransactionOrder> orders;
    
}
