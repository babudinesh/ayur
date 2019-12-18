package com.ayur.payments;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionOrder {

    private String txnId;
    private String orderId;
    private String mercUniqRef;
    private String orderCreatedTime;
    private String orderCompletedTime;
    private String orderType;
    private String orderStatus;
    private String customerName;
    private String customerPhoneNumber;
    private Double txnAmount;
    private String customerComment;
}
