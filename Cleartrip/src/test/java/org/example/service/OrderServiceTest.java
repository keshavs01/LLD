package org.example.service;

import org.example.entity.Order;
import org.example.entity.OrderItem;
import org.example.entity.OrderStatus;
import org.example.exception.OrderException;
import org.example.exception.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest extends FoodService {

    @BeforeEach
    void setUp() {
        init();
    }

    @Test
    void verifyOrderFlow() throws UserNotFoundException, OrderException {
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(new OrderItem(menuItemList.get(0), 1, 30.0));
        Order order = orderService.createOrder(userService.getUser(userId), orderItemList ,restaurantId);

        Assertions.assertEquals(orderService.getOrder(order.getId()), order);

        paymentService.confirmPayment(order.getId());
        Assertions.assertEquals(order.getOrderStatus(), OrderStatus.PAYMENT_SUCCESS);

        restaurantService.confirmOrder(order.getId());
        Assertions.assertEquals(order.getOrderStatus(), OrderStatus.PROCESSING);

        orderService.completeOrder(order.getId());
        Assertions.assertEquals(order.getOrderStatus(), OrderStatus.COMPLETE);
    }

}