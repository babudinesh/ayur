package com.ayur.payments;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionResponse {

    private ResponseHead head;
    private TransactionResponseBody body;
}
