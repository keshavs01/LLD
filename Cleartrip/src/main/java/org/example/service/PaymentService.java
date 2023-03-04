package org.example.service;

import org.example.data.OrderData;
import org.example.entity.Order;
import org.example.exception.OrderException;

import java.util.UUID;

public class PaymentService {

    private OrderData orderData;

    private RestaurantCapacityHandler capacityHandler;


    public PaymentService(OrderData orderData, RestaurantCapacityHandler capacityHandler) {
        this.orderData = orderData;
        this.capacityHandler = capacityHandler;
    }

    public void confirmPayment(UUID orderId) throws OrderException {
        Order order = orderData.getOrder(orderId);
        order.getPayment().successfull();
        order.paymentSuccess();
    }

    public void paymentFailed(UUID orderId) throws OrderException {
        Order order = orderData.getOrder(orderId);
        order.paymentFailed();
        order.getPayment().failed();
        capacityHandler.decreaseCapacity(order.getRestaurantId(), order.getOrderItems().size());
    }

    public void paymentTimeout(UUID orderId) throws OrderException {
        Order order = orderData.getOrder(orderId);
        order.paymentFailed();
        order.getPayment().timeOut();
        capacityHandler.decreaseCapacity(order.getRestaurantId(), order.getOrderItems().size());
    }
}
