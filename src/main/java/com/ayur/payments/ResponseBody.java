package com.ayur.payments;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseBody {

    private String merchantRequestId;
    private String linkId;
    private String linkType;
    private String longUrl;
    private String shortUrl;
    private Double amount;
    private String expiryDate;
    private Boolean isActive;
    private String merchantHtml;
    private String createdDate;
    private List notificationDetails;
    private String clientId;
    private ResultInfo resultInfo;
}
