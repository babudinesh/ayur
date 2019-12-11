package com.ayur.payments;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResultInfo {

    private String resultStatus;
    private String resultCode;
    private String resultMessage;
}
