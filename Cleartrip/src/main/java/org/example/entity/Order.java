package org.example.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode
public class Order {
    private UUID id;
    private List<OrderItem> orderItems;
    private OrderStatus orderStatus;
    private User orderBy;
    private LocalDateTime createdAt;
    private Payment payment;
    private UUID restaurantId;


    public void paymentPending(){
        orderStatus = OrderStatus.PENDING_PAYMENT;
    }

    public void paymentSuccess(){
        orderStatus = OrderStatus.PAYMENT_SUCCESS;
    }

    public void processingOrder(){
        orderStatus = OrderStatus.PROCESSING;
    }

    public void cancelOrder(){
        orderStatus = OrderStatus.CANCELLED;
    }

    public void paymentFailed(){
        orderStatus = OrderStatus.PAYMENT_FAILED;
    }

    public void completeOrder(){
        orderStatus = OrderStatus.COMPLETE;
    }

}
