package org.example.service;

import org.example.data.OrderData;
import org.example.entity.*;
import org.example.exception.OrderException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderService {

    private OrderData orderData;
    private RestaurantCapacityHandler capacityHandler;

    OrderService(OrderData orderData, RestaurantCapacityHandler capacityHandler){
        this.orderData = orderData;
        this.capacityHandler = capacityHandler;
    }

    public Order createOrder(User user, List<OrderItem> orderItems, UUID restaurantId) {
        capacityHandler.increaseCapacity(restaurantId, orderItems.size());
        UUID orderId = UUID.randomUUID();
        Payment payment = new Payment(UUID.randomUUID(), orderId, PaymentStatus.INCOMPLETE, getPaymentAmount(orderItems));
        Order order = Order.builder()
                .id(orderId)
                .restaurantId(restaurantId)
                .orderBy(user)
                .orderItems(orderItems)
                .createdAt(LocalDateTime.now())
                .payment(payment)
                .orderStatus(OrderStatus.PLACED)
                .build();
        orderData.addOrder(order);
        return order;
    }

    private Double getPaymentAmount(List<OrderItem> orderItems){
        Double totalAmt = 0.0;
        for (int i=0;i<orderItems.size();i++){
            totalAmt+=(orderItems.get(i).getPrice() * orderItems.get(i).getQuantity());
        }
        return totalAmt;
    }

    public void completeOrder(UUID orderId) throws OrderException {
        Order order = orderData.getOrder(orderId);
        order.completeOrder();
        capacityHandler.decreaseCapacity(order.getRestaurantId(), order.getOrderItems().size());
    }

    public Order getOrder(UUID orderId) throws OrderException {
        return orderData.getOrder(orderId);
    }

}
