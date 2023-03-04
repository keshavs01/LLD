package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Payment {

    private UUID id;
    private UUID orderId;
    private PaymentStatus paymentStatus;
    private Double amount;

    public void failed(){
        paymentStatus = PaymentStatus.FAILED;
    }

    public void successfull(){
        paymentStatus = PaymentStatus.SUCCESS;
    }

    public void timeOut(){
        paymentStatus = PaymentStatus.TIME_OUT;
    }

}
