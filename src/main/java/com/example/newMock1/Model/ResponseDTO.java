package com.example.newMock1.Model;
import lombok.*;

import java.math.BigDecimal;
import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseDTO {
    private String rqUID;
    private String clientId;
    private String account;
    private String currency;
    private Integer balance;
    private BigDecimal maxLimit;
}
