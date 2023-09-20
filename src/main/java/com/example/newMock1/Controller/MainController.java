package com.example.newMock1.Controller;

import com.example.newMock1.Model.RequestDTO;
import com.example.newMock1.Model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Random;

@RestController
public class MainController {
    private Logger log = LoggerFactory.getLogger(MainController.class);
    ObjectMapper mapper = new ObjectMapper();
    @PostMapping(
            value = "/info/postBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Object postBalances(@RequestBody RequestDTO requestDTO) {
        try {
            String clientId = requestDTO.getClientId();
            char firstDigit = clientId.charAt(0);
            BigDecimal maxLimit;
            Integer curr_balance;
            String currency_val;
            if (firstDigit=='8') {
                maxLimit=new BigDecimal(2000);
                currency_val = "US";
                curr_balance=new Random().nextInt(maxLimit.intValue()+1);
            } else if (firstDigit=='9') {
                maxLimit=new BigDecimal(1000);
                currency_val = "EU";
                curr_balance=new Random().nextInt(maxLimit.intValue()+1);
            } else {
                maxLimit = new BigDecimal(10000);
                curr_balance=new Random().nextInt(maxLimit.intValue()+1);
                currency_val = "RU";
            }
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setRqUID(requestDTO.getRqUID());
            responseDTO.setClientId(clientId);
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setCurrency(currency_val);
            responseDTO.setBalance(curr_balance);
            responseDTO.setMaxLimit(maxLimit);
            log.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));
            return responseDTO;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
